package org.elice.assignment.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elice.assignment.ui.theme.ElicePurple
import org.elice.assignment.ui.theme.EliceRed
import org.elice.assignment.ui.theme.NotoBold

@Composable
fun EliceButton(
    modifier: Modifier = Modifier,
    activateText: String = "",
    deActivateText: String = "",
    isActivate: Boolean,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isActivate) EliceRed else ElicePurple,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = if (isActivate) deActivateText else activateText,
            fontSize = 16.sp,
            fontFamily = NotoBold,
            color = Color.White,
            lineHeight = 24.sp,
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}