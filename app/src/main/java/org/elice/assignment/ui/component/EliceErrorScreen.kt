package org.elice.assignment.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import org.elice.assignment.R
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.ui.theme.EliceDeepPurple
import org.elice.assignment.ui.theme.NotoBold

@Composable
fun EliceErrorScreen(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {}
) {
    val errorLottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.lottie_error)
    )

    Box (modifier = Modifier.fillMaxSize()){
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp),
            text = stringResource(R.string.error_occurred),
            color = EliceDeepPurple,
            fontFamily = NotoBold,
            fontSize = 30.sp
        )
        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            composition = errorLottieComposition,
            iterations = LottieConstants.IterateForever
        )
        EliceButton(
            modifier = modifier
                .fillMaxWidth()
                .height(84.dp)
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            onClick = onRetry,
            isActivate = false,
            activateText = stringResource(R.string.retry)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEliceErrorScreen() {
    AssignmentTheme {
        EliceErrorScreen()
    }
}