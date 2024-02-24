package org.elice.assignment.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "Home")
    object CourseDetail : Screen(route = "CourseDetail")
}