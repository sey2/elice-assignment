package org.elice.assignment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.elice.assignment.ui.navigation.SetUpNavGraph
import org.elice.assignment.ui.theme.AssignmentTheme

@Composable
fun EliceApp() {
    val navHostController = rememberNavController()
    val backStackEntry by navHostController.currentBackStackEntryAsState()

    AssignmentTheme {
        Scaffold { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {
                SetUpNavGraph(navController = navHostController)
            }
        }
    }
}