package org.elice.assignment.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.domain.entities.createMockCourseEntity
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.viewmodel.EliceHomeUiState
import org.elice.assignment.viewmodel.EliceHomeViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: EliceHomeViewModel = hiltViewModel()
) {
    val eliceHomeUiState by homeViewModel.homeState.collectAsStateWithLifecycle()
    val eliceFreeCourseList by homeViewModel.eliceFreeCourseList.collectAsState()
    val eliceRecommendedCourseList by homeViewModel.eliceRecommendedCourseList.collectAsState()

    HomeContent(
        eliceHomeUiState = eliceHomeUiState,
        eliceFreeCourseList = eliceFreeCourseList,
        eliceRecommendedList = eliceRecommendedCourseList
    )
}

@Composable
internal fun HomeContent(
    eliceHomeUiState: EliceHomeUiState,
    eliceFreeCourseList: List<CourseEntity>,
    eliceRecommendedList: List<CourseEntity>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when (eliceHomeUiState) {
            EliceHomeUiState.LOADING -> {
                // todo loading
            }

            EliceHomeUiState.SUCCESS -> {
                Log.d("API CALL", eliceFreeCourseList.toString())

                HomeHeader()
                CourseGridList(
                    courseList = eliceFreeCourseList,
                    title = "무료 과목",
                )
                Spacer(Modifier.padding(vertical = 8.dp))
                CourseGridList(
                    courseList = eliceRecommendedList,
                    title = "추천 과목",
                )
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    AssignmentTheme {
        HomeContent(
            eliceHomeUiState = EliceHomeUiState.SUCCESS,
            eliceFreeCourseList = createMockCourseEntity(),
            eliceRecommendedList = createMockCourseEntity()
        )
    }
}