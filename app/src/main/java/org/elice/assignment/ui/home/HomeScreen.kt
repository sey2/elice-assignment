package org.elice.assignment.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.elice.assignment.R
import org.elice.assignment.domain.entities.CourseEntity
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.viewmodel.EliceHomeUiState
import org.elice.assignment.viewmodel.EliceHomeViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: EliceHomeViewModel = hiltViewModel()
) {
    val eliceHomeUiState by homeViewModel.homeState.collectAsStateWithLifecycle()
    val eliceCourseList by homeViewModel.eliceCourseList.collectAsState()

    HomeContent(
        eliceHomeUiState = eliceHomeUiState,
        eliceCourseList = eliceCourseList
    )
}

@Composable
internal fun HomeContent(
    eliceHomeUiState: EliceHomeUiState,
    eliceCourseList: List<CourseEntity>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HomeHeader()

        when (eliceHomeUiState) {
            EliceHomeUiState.LOADING -> {
                // todo loading
            }

            EliceHomeUiState.SUCCESS -> {
                Log.d("API CALL", eliceCourseList.toString())
            }
        }
    }
}

@Composable
private fun HomeHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "Header Logo",
            modifier = modifier.size(width = 148.dp, height = 32.dp)
        )

        Spacer(modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            modifier = modifier.size(32.dp)
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    AssignmentTheme {
        HomeContent(
            eliceHomeUiState = EliceHomeUiState.SUCCESS,
            eliceCourseList = listOf()
        )
    }
}