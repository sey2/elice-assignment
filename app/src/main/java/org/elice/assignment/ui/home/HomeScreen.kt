package org.elice.assignment.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.viewmodel.CourseListState
import org.elice.assignment.viewmodel.EliceHomeUiState
import org.elice.assignment.viewmodel.EliceHomeViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: EliceHomeViewModel = hiltViewModel()
) {
    val eliceHomeUiState by homeViewModel.homeState.collectAsStateWithLifecycle()
    val courseListState by homeViewModel.courseListState.collectAsStateWithLifecycle()
    val enrolledCoursesState by homeViewModel.enrolledCourseListState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        homeViewModel.getEnrolledCourses()
        homeViewModel.onRefresh()
    }

    HomeContent(
        navHostController = navHostController,
        eliceHomeUiState = eliceHomeUiState,
        courseListState = courseListState,
        enrolledCoursesState = enrolledCoursesState,
        onLoadMoreFreeCourses = { homeViewModel.onLoad(true) },
        onLoadMoreRecommendedCourses = { homeViewModel.onLoad(false) }
    )
}

@Composable
internal fun HomeContent(
    navHostController: NavHostController,
    eliceHomeUiState: EliceHomeUiState,
    courseListState: CourseListState,
    enrolledCoursesState: List<CourseEntity>,
    onLoadMoreFreeCourses: () -> Unit,
    onLoadMoreRecommendedCourses: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        when (eliceHomeUiState) {
            EliceHomeUiState.LOADING -> {
                // Todo
            }

            EliceHomeUiState.SUCCESS -> {
                HomeHeader()
                CourseGridList(
                    navHostController,
                    courseList = courseListState.freeCourseList,
                    title = "무료 과목",
                    onLoadMore = onLoadMoreFreeCourses
                )
                Spacer(Modifier.padding(vertical = 8.dp))
                CourseGridList(
                    navHostController,
                    courseList = courseListState.recommendedCourseList,
                    title = "추천 과목",
                    onLoadMore = onLoadMoreRecommendedCourses
                )
                Spacer(Modifier.padding(vertical = 8.dp))
                CourseGridList(
                    navHostController,
                    courseList = enrolledCoursesState,
                    title = "내 학습",
                    onLoadMore = onLoadMoreRecommendedCourses
                )
            }

            EliceHomeUiState.EMPTY -> {
                // Todo
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    AssignmentTheme {
        HomeContent(
            navHostController = rememberNavController(),
            eliceHomeUiState = EliceHomeUiState.SUCCESS,
            courseListState = CourseListState( 1, 1),
            enrolledCoursesState = listOf(),
            onLoadMoreFreeCourses = {},
            onLoadMoreRecommendedCourses = {}
        )
    }
}