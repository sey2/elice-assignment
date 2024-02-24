package org.elice.assignment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.elice.assignment.detail.CourseDetailScreen
import org.elice.assignment.home.HomeScreen

@Composable
fun SetUpNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navHostController = navController)
        }
        composable(
            route = Screen.CourseDetail.route
        ) {
            CourseDetailScreen(navHostController = navController)
        }
    }
}
