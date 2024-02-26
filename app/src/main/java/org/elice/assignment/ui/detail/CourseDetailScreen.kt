package org.elice.assignment.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.elice.assignment.domain.entities.CourseDetailEntity
import org.elice.assignment.domain.entities.LectureEntity
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.ui.theme.ElicePurple
import org.elice.assignment.ui.theme.EliceRed
import org.elice.assignment.ui.theme.NotoBold
import org.elice.assignment.viewmodel.EliceCourseDetailUiState
import org.elice.assignment.viewmodel.EliceCourseDetailViewModel

@Composable
internal fun CourseDetailScreen(
    navHostController: NavHostController,
    courseId: String?,
    courseDetailViewModel: EliceCourseDetailViewModel = hiltViewModel()
) {
    val courseDetailUiState by courseDetailViewModel.courseDetailUiState.collectAsStateWithLifecycle()
    val courseDetailState by courseDetailViewModel.courseDetailState.collectAsStateWithLifecycle()
    val lectureListState by courseDetailViewModel.lectureListState.collectAsStateWithLifecycle()
    val isEnrolled by courseDetailViewModel.isEnrollCourseState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        courseId?.let { id ->
            courseDetailViewModel.loadData(id.toInt())
        }
    }

    CourseDetailContent(
        navHostController = navHostController,
        courseDetailUiState = courseDetailUiState,
        courseDetailState = courseDetailState,
        lectureListState = lectureListState,
        onEnrollClick = { courseId?.let { courseDetailViewModel.enrollButtonClick(it.toInt()) } },
        isEnrolled = isEnrolled
    )
}

@Composable
internal fun CourseDetailContent(
    navHostController: NavHostController,
    courseDetailUiState: EliceCourseDetailUiState,
    courseDetailState: CourseDetailEntity?,
    lectureListState: List<LectureEntity>,
    onEnrollClick: ()-> Unit = {},
    isEnrolled: Boolean = false
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (courseDetailUiState) {
            EliceCourseDetailUiState.LOADING -> {
                // Todo
            }

            EliceCourseDetailUiState.SUCCESS -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 64.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowLeft,
                            contentDescription = "Back Button",
                            modifier = Modifier
                                .size(42.dp)
                                .clickable { navHostController.popBackStack() }
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        if (courseDetailState?.imageFileUrl == null) {
                            CourseTitleAreaWithoutImage(courseDetail = courseDetailState)
                        } else {
                            CourseTitleAreaWithImage(courseDetail = courseDetailState)
                        }
                        if (courseDetailState?.description != "") {
                            CourseDetailDescriptionArea(description = courseDetailState?.description ?: "")
                        }
                        CourseDetailCurriculumArea(lectures = lectureListState)
                    }
                }

                EnrollButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(84.dp)
                        .padding(16.dp),
                    isEnrolled = isEnrolled,
                    onClick = { onEnrollClick() }
                )
            }
        }
    }
}

@Composable
fun EnrollButton(
    modifier: Modifier,
    isEnrolled: Boolean,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnrolled) EliceRed else ElicePurple,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = if (isEnrolled) "수강 취소" else "수강 신청",
            fontSize = 16.sp,
            fontFamily = NotoBold,
            lineHeight = 24.sp,
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseDetailScreen() {
    AssignmentTheme {
        CourseDetailContent(
            navHostController = rememberNavController(),
            courseDetailUiState = EliceCourseDetailUiState.SUCCESS,
            courseDetailState = null,
            lectureListState = listOf()
        )
    }
}