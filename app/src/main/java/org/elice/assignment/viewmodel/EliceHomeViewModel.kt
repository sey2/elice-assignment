package org.elice.assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.usecase.course.GetEliceCourseList
import javax.inject.Inject

@HiltViewModel
class EliceHomeViewModel @Inject constructor(
    private val getEliceCourseList: GetEliceCourseList
) : ViewModel() {

    private val _eliceFreeCourseList: MutableStateFlow<List<CourseEntity>> =
        MutableStateFlow(mutableListOf())
    val eliceFreeCourseList: StateFlow<List<CourseEntity>> = _eliceFreeCourseList

    private val _eliceRecommendedCourseList: MutableStateFlow<List<CourseEntity>> =
        MutableStateFlow(mutableListOf())
    val eliceRecommendedCourseList: StateFlow<List<CourseEntity>> = _eliceRecommendedCourseList

    private val _homeState: MutableStateFlow<EliceHomeUiState> =
        MutableStateFlow(EliceHomeUiState.LOADING)
    val homeState: StateFlow<EliceHomeUiState> = _homeState

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            getEliceCourseList(
                offset = 0,
                count = 10,
                filterIsRecommended = null,
                filterIsFree = true
            ).collectLatest { courseList ->
                _eliceFreeCourseList.value = courseList
            }

            getEliceCourseList(
                offset = 0,
                count = 10,
                filterIsRecommended = true,
                filterIsFree = null
            ).collectLatest { courseList ->
                _eliceRecommendedCourseList.value = courseList
            }

            _homeState.value = EliceHomeUiState.SUCCESS
        }
    }
}

enum class EliceHomeUiState {
    LOADING,
    SUCCESS,
}