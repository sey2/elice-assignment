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
import org.elice.assignment.domain.usecase.course.GetEliceCourseList
import javax.inject.Inject

@HiltViewModel
class EliceHomeViewModel @Inject constructor(
    private val getEliceCourseList: GetEliceCourseList
) : ViewModel() {

    private val _courseListState: MutableStateFlow<CourseListState> =
        MutableStateFlow(CourseListState(1, 1))
    val courseListState: StateFlow<CourseListState> = _courseListState.asStateFlow()

    private val _homeState: MutableStateFlow<EliceHomeUiState> =
        MutableStateFlow(EliceHomeUiState.LOADING)
    val homeState: StateFlow<EliceHomeUiState> = _homeState

    fun onLoad(isFree: Boolean, refresh: Boolean = false) {
        val currentOffset =
            if (isFree) courseListState.value.freePage * 10 else courseListState.value.recommendedPage * 10
        viewModelScope.launch {
            getEliceCourseList(
                offset = currentOffset,
                count = 10,
                filterIsFree = isFree
            ).collectLatest { courseList ->
                if (courseList.isEmpty() && refresh) {
                    _homeState.value = EliceHomeUiState.EMPTY
                } else {
                    updateCourseList(courseList, isFree)
                    _homeState.value = EliceHomeUiState.SUCCESS
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
}