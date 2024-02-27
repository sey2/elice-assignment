package org.elice.assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.elice.assignment.domain.entities.CourseDetailEntity
import org.elice.assignment.domain.entities.LectureEntity
import org.elice.assignment.domain.model.ApiResult
import org.elice.assignment.domain.usecase.local.AddEnrollCourse
import org.elice.assignment.domain.usecase.local.DeleteEnrolledCourse
import org.elice.assignment.domain.usecase.local.IsEnrolledCourse
import org.elice.assignment.domain.usecase.remote.course.GetEliceCourse
import org.elice.assignment.domain.usecase.remote.lecture.GetEliceLectureList
import javax.inject.Inject

@HiltViewModel
class EliceCourseDetailViewModel @Inject constructor(
    private val getEliceCourse: GetEliceCourse,
    private val getEliceLectures: GetEliceLectureList,
    private val addEnrollCourse: AddEnrollCourse,
    private val isEnrolledCourse: IsEnrolledCourse,
    private val deleteEnrolledCourse: DeleteEnrolledCourse
) : ViewModel() {
    private val _courseDetailState: MutableStateFlow<CourseDetailEntity?> =
        MutableStateFlow(null)
    val courseDetailState: StateFlow<CourseDetailEntity?> = _courseDetailState

    private val _lectureListState: MutableStateFlow<List<LectureEntity>> =
        MutableStateFlow(listOf())
    val lectureListState: StateFlow<List<LectureEntity>> = _lectureListState

    private val _courseDetailUiState: MutableStateFlow<EliceCourseDetailUiState> =
        MutableStateFlow(EliceCourseDetailUiState.LOADING)
    val courseDetailUiState: StateFlow<EliceCourseDetailUiState> = _courseDetailUiState

    private val _isEnrollCourseState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val isEnrollCourseState: StateFlow<Boolean> = _isEnrollCourseState

    fun loadData(courseId: Int) {
        _courseDetailUiState.value = EliceCourseDetailUiState.LOADING
        viewModelScope.launch {
            try {
                getCourseDetail(courseId)
                getLectures(courseId)
                findEnrolledCourse(courseId)
                _courseDetailUiState.value = EliceCourseDetailUiState.SUCCESS
            } catch (e: Exception) {
                _courseDetailUiState.value = EliceCourseDetailUiState.ERROR
            }
        }
    }

    private suspend fun getCourseDetail(courseId: Int) {
        getEliceCourse(courseId).collectLatest { apiResult ->
            when(apiResult) {
                is ApiResult.Success -> {
                    _courseDetailState.value = apiResult.value
                }
                else -> {
                    _courseDetailUiState.value = EliceCourseDetailUiState.ERROR
                    viewModelScope.cancel()
                }
            }
        }
    }

    private suspend fun getLectures(courseId: Int) {
        getEliceLectures(
            offset = 0,
            count = 10,
            courseId
        ).collectLatest { apiResult ->
            when(apiResult) {
                is ApiResult.Success -> {
                    _lectureListState.value = apiResult.value
                }
                else -> {
                    _courseDetailUiState.value = EliceCourseDetailUiState.ERROR
                    viewModelScope.cancel()
                }
            }
        }
    }

    fun enrollButtonClick(courseId: Int) {
        viewModelScope.launch {
            if(isEnrollCourseState.value) {
                deleteEnrolledCourse(courseId)
            } else {
                addEnrollCourse(courseId)
            }
            _isEnrollCourseState.value = !isEnrollCourseState.value
        }
    }

    private suspend fun findEnrolledCourse(courseId: Int) {
        isEnrolledCourse(courseId).collectLatest { isEnrolled ->
            _isEnrollCourseState.value = isEnrolled
        }
    }

}

enum class EliceCourseDetailUiState {
    LOADING,
    SUCCESS,
    ERROR
}