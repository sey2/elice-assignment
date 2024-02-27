package org.elice.assignment.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.elice.assignment.R
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.ui.error.EliceErrorScreen
import org.elice.assignment.ui.component.EliceLoadingWheel
import org.elice.assignment.ui.navigation.Screen
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

    var retryClickState by rememberSaveable { mutableStateOf(false) }

    HomeContent(
        navHostController = navHostController,
        eliceHomeUiState = eliceHomeUiState,
        courseListState = courseListState,
        enrolledCoursesState = enrolledCoursesState,
        onSearchClick = { navHostController.navigate(Screen.UnReady.route) },
        onRetryCourses = { retryClickState = !retryClickState },
        onLoadMoreFreeCourses = { homeViewModel.onLoad(isFree = true, isRecommended = null) },
        onLoadMoreRecommendedCourses = { homeViewModel.onLoad(isFree = null, isRecommended = true) }
    )
}

@Composable
internal fun HomeContent(
    navHostController: NavHostController,
    eliceHomeUiState: EliceHomeUiState,
    courseListState: CourseListState,
    enrolledCoursesState: List<CourseEntity>,
    onSearchClick: () -> Unit,
    onRetryCourses: () -> Unit,
    onLoadMoreFreeCourses: () -> Unit,
    onLoadMoreRecommendedCourses: () -> Unit
) {
    when (eliceHomeUiState) {
        EliceHomeUiState.LOADING -> {
            Box(Modifier.fillMaxSize()) {
                EliceLoadingWheel(modifier = Modifier.align(Alignment.Center))
            }
        }

        EliceHomeUiState.SUCCESS -> {
            HomeSuccessScreen(
                navHostController = navHostController,
                courseListState = courseListState,
                enrolledCoursesState = enrolledCoursesState,
                onSearchClick = onSearchClick,
                onLoadMoreFreeCourses = onLoadMoreFreeCourses,
                onLoadMoreRecommendedCourses = onLoadMoreRecommendedCourses
            )
        }

        EliceHomeUiState.EMPTY -> {
            // Noting
        }

        EliceHomeUiState.ERROR -> {
            EliceErrorScreen(onRetry = onRetryCourses)
        }
    }
}

@Composable
private fun HomeSuccessScreen(
    navHostController: NavHostController,
    courseListState: CourseListState,
    enrolledCoursesState: List<CourseEntity>,
    onSearchClick: () -> Unit,
    onLoadMoreFreeCourses: () -> Unit,
    onLoadMoreRecommendedCourses: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeader(onSearchClick = onSearchClick)
        CourseGridList(
            navHostController,
            courseList = courseListState.freeCourseList,
            title = stringResource(R.string.free_course),
            onLoadMore = onLoadMoreFreeCourses
        )
        Spacer(Modifier.padding(vertical = 8.dp))
        CourseGridList(
            navHostController,
            courseList = courseListState.recommendedCourseList,
            title = stringResource(R.string.recommended_course),
            onLoadMore = onLoadMoreRecommendedCourses
        )
        Spacer(Modifier.padding(vertical = 8.dp))
        if (enrolledCoursesState.isNotEmpty()) {
            CourseGridList(
                navHostController,
                courseList = enrolledCoursesState,
                title = stringResource(R.string.my_course)
            )
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
            courseListState = CourseListState(1, 1),
            enrolledCoursesState = listOf(),
            onSearchClick = {},
            onRetryCourses = {},
            onLoadMoreFreeCourses = {},
            onLoadMoreRecommendedCourses = {}
        )
    }
}