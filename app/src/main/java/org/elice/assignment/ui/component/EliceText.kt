package org.elice.assignment.ui.component

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.elice.assignment.util.markdownToHtml

@Composable
fun MarkdownText(
    markdownText: String,
    modifier: Modifier = Modifier
) {
    val htmlText = markdownText.markdownToHtml()

    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                loadDataWithBaseURL(null, htmlText, "text/html", "UTF-8", null)
            }
        }
    )
}