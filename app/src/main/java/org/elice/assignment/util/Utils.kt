package org.elice.assignment.util

import android.content.Context
import coil.request.ImageRequest
import com.google.gson.Gson
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.elice.assignment.R
import org.elice.assignment.domain.entities.LectureEntity

fun String.markdownToHtml(): String {
    val parser = Parser.builder().build()
    val document = parser.parse(this)
    val renderer = HtmlRenderer.builder().build()

    return renderer.render(document)
}

fun List<Int>.toJson(): String
    = Gson().toJson(mapOf("course_ids" to this))

fun Context.eliceImageBuilder(imageUrl: String): ImageRequest
    = ImageRequest.Builder(this)
    .data(imageUrl)
    .crossfade(true)
    .error(R.drawable.img_no)
    .build()

fun createMockMarkDownText(): String = "### **불필요한 복잡한 이론은 이제 그만**\r\n" +
        "기존에 딥러닝을 배우는 과정은 퍼셉트론, 선형, 비선형 등 복잡하고 모델을 만드는 전문 연구자에게 필요한 내용이 많았습니다. " +
        "이 과목은 모델을 사용하는 과정에 집중해서 여러 최신 모델을 다뤄봅니다.\r\n\r\n### **생성 AI에 입문하기**\r\n" +
        "텍스트와 이미지를 생성하는 AI 모델을 불러와 사용하는 과정을 알아봅니다. " +
        "간단한 코드로 함께 최신 생성 AI를 불러와 사용해 봅니다.\r\n\r\n" +
        "### **AI 입문자를 위한 기초 이론**\r\n" +
        "AI 분야에서 자주 등장하는 모델, 가중치, 학습 등의 용어가 무엇인지 알아봅니다. " +
        "다른 강의처럼 수식과 복잡한 이론을 통한 설명이 아닌 비유와 예시를 통해 꼭 필요한 내용만 이해해 봅니다.\r\n\r\n" +
        "### **환경설정 없이 바로 코드 실습**\r\n엘리스의 강력한 Runbox를 통해 환경설정 없이 바로 코드를 작성하며 모델을 사용해 봅니다. " +
        "더 이상 CUDA 설치, 버전 관리 등에 시간을 낭비하지 마세요!"

fun createMockLectures(): List<LectureEntity> = listOf(
    LectureEntity(1, "첫 번째 이야기", "가장 기초적인 문제로 이루어져 있습니다. 한번 부담없이 풀어보시는건 어떨까요?"),
    LectureEntity(2, "두 번째 이야기", "반복문, 조건문을 사용해서 풀어야 하는 문제들 입니다."),
    LectureEntity(3, "세 번째 이야기", "배열과 문자열을 다루기도 하고, 반복문, 조건문을 복잡하게 다루는 조금 어려운 문제입니다."),
    LectureEntity(4, "네 번째 이야기",  "C언어의 조건문, 반복문, 배열 등은 물론 라이브러리에 있는 함수들까지 사용해야 하는 문제입니다. 한번 도전해 봅시다!")
)