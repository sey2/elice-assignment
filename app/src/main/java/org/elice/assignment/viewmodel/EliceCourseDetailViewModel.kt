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
import org.elice.assignment.domain.usecase.course.GetEliceCourse
import javax.inject.Inject

@HiltViewModel
class EliceCourseDetailViewModel @Inject constructor(
    private val getEliceCourse: GetEliceCourse
) : ViewModel() {
    private val _courseDetailState: MutableStateFlow<CourseDetailEntity?> =
        MutableStateFlow(null)
    val courseDetail: StateFlow<CourseDetailEntity?> = _courseDetailState

    private val _courseDetailUiState: MutableStateFlow<EliceCourseDetailUiState> =
        MutableStateFlow(EliceCourseDetailUiState.LOADING)
    val courseDetailUiState: StateFlow<EliceCourseDetailUiState> = _courseDetailUiState

    fun getCourseDetail(courseId: Int) {
        viewModelScope.launch {
            getEliceCourse(courseId).collectLatest { courseDetail ->
                _courseDetailState.value = courseDetail
                _courseDetailUiState.value = EliceCourseDetailUiState.SUCCESS
            }
            Log.d("detail", courseDetail.value.toString())
        }
    }

}

enum class EliceCourseDetailUiState {
    LOADING,
    SUCCESS
}