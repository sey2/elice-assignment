package org.elice.assignment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.elice.assignment.domain.entities.CourseDetailEntity
import org.elice.assignment.domain.entities.LectureEntity
import org.elice.assignment.domain.usecase.course.GetEliceCourse
import org.elice.assignment.domain.usecase.course.GetEliceLectureList
import javax.inject.Inject

@HiltViewModel
class EliceCourseDetailViewModel @Inject constructor(
    private val getEliceCourse: GetEliceCourse,
    private val getEliceLectures: GetEliceLectureList
) : ViewModel() {
    private val _courseDetailState: MutableStateFlow<CourseDetailEntity?> =
        MutableStateFlow(null)
    val courseDetail: StateFlow<CourseDetailEntity?> = _courseDetailState

    private val _lectureListState: MutableStateFlow<List<LectureEntity>> =
        MutableStateFlow(listOf())
    val lectureListState: StateFlow<List<LectureEntity>> = _lectureListState

    private val _courseDetailUiState: MutableStateFlow<EliceCourseDetailUiState> =
        MutableStateFlow(EliceCourseDetailUiState.LOADING)
    val courseDetailUiState: StateFlow<EliceCourseDetailUiState> = _courseDetailUiState

    fun getCourseDetail(courseId: Int) {
        viewModelScope.launch {
            getEliceCourse(courseId).collectLatest { courseDetail ->
                _courseDetailState.value = courseDetail
                _courseDetailUiState.value = EliceCourseDetailUiState.SUCCESS
            }
        }
    }

    fun getLectures(courseId: Int) {
        viewModelScope.launch {
            getEliceLectures(
                offset = 0,
                count = 10,
                courseId
            ).collectLatest { currentLectureList ->
                _lectureListState.value = currentLectureList
                _courseDetailUiState.value = EliceCourseDetailUiState.SUCCESS
            }
        }
    }

}

enum class EliceCourseDetailUiState {
    LOADING,
    SUCCESS
}