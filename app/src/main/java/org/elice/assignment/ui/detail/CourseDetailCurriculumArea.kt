package org.elice.assignment.ui.detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elice.assignment.domain.entities.LectureEntity
import org.elice.assignment.ui.theme.AssignmentTheme
import org.elice.assignment.ui.theme.EliceDeepPurple
import org.elice.assignment.ui.theme.ElicePurple
import org.elice.assignment.ui.theme.NotoBold
import org.elice.assignment.ui.theme.NotoRegular
import org.elice.assignment.util.createMockLectures

@Composable
fun CourseDetailCurriculumArea(
    modifier: Modifier = Modifier,
    lectures: List<LectureEntity>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 10.dp)
    ) {
        Text(
            text = "커리 큘럼",
            fontSize = 14.sp,
            fontFamily = NotoBold,
            lineHeight = 20.sp,
            color = EliceDeepPurple,
            modifier = Modifier.padding(top = 8.dp)
        )
        Divider(modifier = Modifier.padding(vertical = 10.dp))

        TimelineView(
            lectures = lectures,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}

@Composable
fun TimelineView(
    lectures: List<LectureEntity>,
    modifier: Modifier = Modifier,
    circleColor: Color = ElicePurple,
    lineColor: Color = ElicePurple,
    circleRadius: Dp = 8.dp,
    lineWidth: Dp = 2.dp
) {
    Column(modifier = modifier) {
        repeat(lectures.size) { index ->
            TimelineItemView(
                title = lectures[index].title,
                description = lectures[index].description,
                circleColor = circleColor,
                lineColor = lineColor,
                circleRadius = circleRadius,
                lineWidth = lineWidth,
                isLastItem = index == lectures.size - 1
            )
        }
    }
}

@Composable
fun TimelineItemView(
    title: String,
    description: String,
    circleColor: Color,
    lineColor: Color,
    circleRadius: Dp,
    lineWidth: Dp,
    isLastItem: Boolean
) {
    var titleHeight by remember { mutableStateOf(0f) }
    var descriptionHeight by remember { mutableStateOf(0f) }
    var textColumnHeight by remember { mutableStateOf(0f) }

    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Canvas(
            modifier = Modifier
                .width(16.dp)
                .height(with(LocalDensity.current) { textColumnHeight.toDp() })
        ) {
            val canvasWidth = size.width
            val circleStrokeWidth = lineWidth.toPx()
            val circleStrokeRadius = circleRadius.toPx()

            drawCircle(
                color = circleColor,
                radius = circleStrokeRadius,
                center = Offset(x = canvasWidth / 2, y = titleHeight / 2),
                style = Stroke(width = circleStrokeWidth)
            )

            if (!isLastItem) {
                val spaceBetweenCircles = 20.dp.toPx()
                drawLine(
                    color = lineColor,
                    start = Offset(x = canvasWidth / 2,  y = circleStrokeRadius * 3 - 5f),
                    end = Offset(x = canvasWidth / 2, y = textColumnHeight  + spaceBetweenCircles),
                    strokeWidth = circleStrokeWidth
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.onGloballyPositioned { coordinates ->
                val newHeight = coordinates.size.height.toFloat()
                if (textColumnHeight != newHeight) {
                    textColumnHeight = newHeight
                }
            }
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontFamily = NotoBold,
                lineHeight = 28.sp,
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        val newHeight = coordinates.size.height.toFloat()
                        if (titleHeight != newHeight) {
                            titleHeight = newHeight
                        }
                    }
            )
            Text(
                text = description,
                fontSize = 14.sp,
                fontFamily = NotoRegular,
                lineHeight = 20.sp,
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    val newHeight = coordinates.size.height.toFloat()
                    if (descriptionHeight != newHeight) {
                        descriptionHeight = newHeight
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseDetailCurriculumArea() {
    AssignmentTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 10.dp)
        ) {
            Text(
                text = "커리 큘럼",
                fontSize = 14.sp,
                fontFamily = NotoBold,
                lineHeight = 20.sp,
                color = EliceDeepPurple,
                modifier = Modifier.padding(top = 8.dp)
            )
            Divider(modifier = Modifier.padding(vertical = 10.dp))

            TimelineView(
                lectures = createMockLectures(),
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}