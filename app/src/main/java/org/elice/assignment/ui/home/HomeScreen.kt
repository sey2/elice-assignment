package org.elice.assignment.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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

    LaunchedEffect(true) {
        homeViewModel.onRefresh()
    }

    HomeContent(
        navHostController = navHostController,
        eliceHomeUiState = eliceHomeUiState,
        courseListState = courseListState,
        onLoadMoreFreeCourses = { homeViewModel.onLoad(true) },
        onLoadMoreRecommendedCourses = { homeViewModel.onLoad(false) }
    )
}

@Composable
internal fun HomeContent(
    navHostController: NavHostController,
    eliceHomeUiState: EliceHomeUiState,
    courseListState: CourseListState,
    onLoadMoreFreeCourses: () -> Unit,
    onLoadMoreRecommendedCourses: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
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
            onLoadMoreFreeCourses = {},
            onLoadMoreRecommendedCourses = {}
        )
    }
}