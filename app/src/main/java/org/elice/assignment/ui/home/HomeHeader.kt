package org.elice.assignment.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.elice.assignment.R

@Composable
internal fun HomeHeader(modifier: Modifier = Modifier) {
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
