package org.elice.assignment.viewmodel

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
import org.elice.assignment.domain.usecase.lecture.GetEliceLectureList
import javax.inject.Inject

@HiltViewModel
class EliceCourseDetailViewModel @Inject constructor(
    private val getEliceCourse: GetEliceCourse,
    private val getEliceLectures: GetEliceLectureList
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

    fun loadData(courseId: Int) {
        _courseDetailUiState.value = EliceCourseDetailUiState.LOADING
        viewModelScope.launch {
            val courseDetailJob = launch { getCourseDetail(courseId) }
            val lecturesJob = launch { getLectures(courseId) }

            courseDetailJob.join()
            lecturesJob.join()

            _courseDetailUiState.value = EliceCourseDetailUiState.SUCCESS
        }
    }


    private suspend fun getCourseDetail(courseId: Int) {
        getEliceCourse(courseId).collectLatest { courseDetail ->
            _courseDetailState.value = courseDetail
        }

    }

    private suspend fun getLectures(courseId: Int) {
        getEliceLectures(
            offset = 0,
            count = 10,
            courseId
        ).collectLatest { currentLectureList ->
            _lectureListState.value = currentLectureList
        }
    }


}

enum class EliceCourseDetailUiState {
    LOADING,
    SUCCESS
}