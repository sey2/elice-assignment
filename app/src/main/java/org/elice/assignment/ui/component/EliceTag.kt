package org.elice.assignment.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elice.assignment.ui.theme.NotoBold

@Composable
internal fun EliceTag(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Surface(
        modifier = modifier.padding(top = 8.dp),
        color = Color(0xFFE4E4E4),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text,
            modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontSize = 8.sp,
            fontFamily = NotoBold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}