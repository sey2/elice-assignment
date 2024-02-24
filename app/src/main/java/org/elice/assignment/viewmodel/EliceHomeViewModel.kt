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

    private val _eliceCourseList: MutableStateFlow<List<CourseEntity>> =
        MutableStateFlow(mutableListOf())
    val eliceCourseList: StateFlow<List<CourseEntity>> = _eliceCourseList

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
                filterIsFree = null
            ).collectLatest { courseList ->
                _eliceCourseList.value = courseList
                _homeState.value = EliceHomeUiState.SUCCESS
            }
        }
    }
}

enum class EliceHomeUiState {
    LOADING,
    SUCCESS
}