package org.elice.assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.model.ApiResult
import org.elice.assignment.domain.usecase.local.GetEnrollCourses
import org.elice.assignment.domain.usecase.remote.course.GetEliceCourseList
import org.elice.assignment.util.toJson
import javax.inject.Inject

@HiltViewModel
class EliceHomeViewModel @Inject constructor(
    private val getEliceCourseList: GetEliceCourseList,
    private val getEnrolledCourseList: GetEnrollCourses,
) : ViewModel() {

    private val _courseListState: MutableStateFlow<CourseListState> =
        MutableStateFlow(CourseListState(1, 1))
    val courseListState: StateFlow<CourseListState> = _courseListState.asStateFlow()

    private val _enrolledCourseIdListState: MutableStateFlow<List<Int>> =
        MutableStateFlow(listOf())
    val enrolledCourseIdListState: StateFlow<List<Int>> = _enrolledCourseIdListState

    private val _enrolledCourseListState: MutableStateFlow<List<CourseEntity>> =
        MutableStateFlow(listOf())
    val enrolledCourseListState: StateFlow<List<CourseEntity>> = _enrolledCourseListState

    private val _homeState: MutableStateFlow<EliceHomeUiState> =
        MutableStateFlow(EliceHomeUiState.LOADING)
    val homeState: StateFlow<EliceHomeUiState> = _homeState

    init {
        viewModelScope.launch {
            getEnrolledCourses()
            onRefresh()
        }
    }

    private suspend fun getEnrolledCourseIds() {
        getEnrolledCourseList().collectLatest { enrolledList ->
            _enrolledCourseIdListState.value = enrolledList
        }
    }

    suspend fun getEnrolledCourses() {
        viewModelScope.launch {
            val enrolledCourseIdsJob = launch { getEnrolledCourseIds() }
            enrolledCourseIdsJob.join()

            getEliceCourseList(
                offset = 0,
                count = 10,
                filterConditions = enrolledCourseIdListState.value.toJson()
            ).collectLatest { apiResult ->
                when(apiResult) {
                    is ApiResult.Success -> {
                        _enrolledCourseListState.value = apiResult.value
                        _homeState.value = EliceHomeUiState.SUCCESS
                    } else -> _homeState.value = EliceHomeUiState.ERROR
                }
            }
        }
    }

    fun onLoad(isFree: Boolean, refresh: Boolean = false) {
        val currentOffset =
            if (isFree) courseListState.value.freePage * 10 else courseListState.value.recommendedPage * 10

        viewModelScope.launch {
            getEliceCourseList(
                offset = currentOffset,
                count = 10,
                filterIsFree = isFree
            ).collectLatest { apiResult ->
                when(apiResult) {
                    is ApiResult.Success -> {
                        if (apiResult.value.isEmpty() && refresh) {
                            _homeState.value = EliceHomeUiState.EMPTY
                        } else {
                            updateCourseList(apiResult.value, isFree)
                            _homeState.value = EliceHomeUiState.SUCCESS
                        }
                    } else -> {
                        _homeState.value = EliceHomeUiState.ERROR
                    }
                }
            }
        }
    }

    private fun updateCourseList(courseList: List<CourseEntity>, isFree: Boolean) {
        _courseListState.update { currentState ->
            if (isFree) {
                currentState.copy(
                    freeCourseList = currentState.freeCourseList + courseList,
                    freePage = currentState.freePage + (courseList.size / 10)
                )
            } else {
                currentState.copy(
                    recommendedCourseList = currentState.recommendedCourseList + courseList,
                    recommendedPage = currentState.recommendedPage + (courseList.size / 10)
                )
            }
        }
    }

    fun onRefresh() {
        onLoad(isFree = true, refresh = true)
        onLoad(isFree = false, refresh = true)
    }

    fun onClearUiState() {
        _homeState.value = EliceHomeUiState.LOADING
        _courseListState.value = CourseListState(1, 1)
    }

}

data class CourseListState(
    val freePage: Int,
    val recommendedPage: Int,
    val freeCourseList: List<CourseEntity> = listOf(),
    val recommendedCourseList: List<CourseEntity> = listOf()
)

enum class EliceHomeUiState {
    LOADING,
    SUCCESS,
    EMPTY,
    ERROR
}