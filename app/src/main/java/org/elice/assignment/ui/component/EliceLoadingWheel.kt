package org.elice.assignment.ui.component


import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
internal fun EliceLoadingWheel(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .size(120.dp)
    )
}