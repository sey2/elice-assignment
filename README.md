## :rabbit: 엘리스 안드로이드 과제 (24.02.24 ~ 24.02.27)
### 엘리스 Android 개발자 (채용) 미니 프로젝트에 참여할 기회를 주신 것에 감사 드립니다.

### 데모 영상
https://youtu.be/4C72eQpyf-0

--- 
### 디자인 요구 사항 구현 화면
 <table>
  <tr>
    <td><img width="300" src="https://github.com/sey2/elice-assignment/assets/54762273/af95ffd8-b2d9-4b6a-863a-5dd8dd9f4ceb"></td>
   <td><img width="300" src="https://github.com/sey2/elice-assignment/assets/54762273/a81b7c93-a535-4d9c-b0e5-fc863c9eee89"></td>
   <td><img width="300" src="https://github.com/sey2/elice-assignment/assets/54762273/65c20206-cb1b-4a9d-b487-2322dae7a43c"></td>
  </tr>
  <tr>
    <td align="center"><b> 홈 화면 </b></td>
    <td align="center"><b> 과목 상세 - With Image </b></td>
    <td align="center"><b> 과목 상세 - Without Image </b></td>
  </tr>
</table>

### 추가 구현 화면
 <table>
  <tr>
    <td><img width="300" src="https://github.com/sey2/elice-assignment/assets/54762273/72662e20-e4c4-4647-8751-8f0729d48954"></td>
   <td><img width="300" src="https://github.com/sey2/elice-assignment/assets/54762273/cf37e7f9-485d-41c1-883e-95da964d0f6c"></td>
   <td><img width="300" src="https://github.com/sey2/elice-assignment/assets/54762273/3bb7d496-f640-4f19-89c9-21a45ab8163e"></td>
  </tr>
  <tr>
    <td align="center"><b> 검색 아이콘 클릭시 </b></td>
    <td align="center"><b> 오류 화면 </b></td>
    <td align="center"><b> 로딩 화면 </b></td>
  </tr>
</table>

## 개발 방식
- Git Flow Branch 전략을 사용해 feat, develop, hotfix로 브랜치를 나누어서 개발 했습니다.
- Issue 작성 후 PR을 통해 브랜치 병합
- Issue, PR (1:1)로 묶어서 개발 진행

## ❄️ 구현 기능

### 🏡 홈 화면
- 홈 화면 UI
- 과목 리스트 API 기능
- API 호출 오류 발생 시 보여줄 Lottie 화면
- Room을 활용한 내 학습 리스트 불러오기
- 검색 아이콘을 누르면 보여줄 Lottie 화면
- 데이터 로딩 시 보여줄 Circle Inicator
- 무한 스크롤을 활용해 10개 단위로 과목 리스트를 가져오는 Paging 기능

### 📙 과목 상세 화면
- 과목 상세 화면
- 과목 조회 API 기능
- 수업 리스트 API 기능
- Room을 활용한 수강 신청, 수강 취소 기능
- Curriculum Indicator
  -> Canvas를 활용해 title, decription 크기에 따라 동적으로 그려지도록 구현
- Markdown View
- 홈 화면과 동일하게 에러 처리 화면 구현
- image_file_url 여부에 따라 TitleArea 구현

## : package: 프로젝트 구조

| Name | Responsibilities | 
| --- | --- |
| data | core:network 및 local datasource로 부터 앱 데이터를 가져옵니다. |
| domain | Clean architecture의 Domain layer에 해당하는 영역으로 종속성 주입(DI)를 위한 인터페이스와 비즈니스 로직을 포함한 UseCase를 포함합니다. |
| network | 네트워크 요청을 만들고 응답을 처리합니다. |
| ui | 앱에서 공통적으로 사용되는 UI 구성요소입니다. |
| viewmodel | 앱에서 사용하는 ViewModel 구성 요소입니다.|
| util | 유틸 함수가 들어 있습니다. |

## 🛠 아키텍처

![15](https://github.com/sey2/elice-assignment/assets/54762273/47463780-ee8d-4723-a5a6-54b68c606777)


Clean Architecture, MVVM 아키텍처 구조 입니다. <br>
크게 UI -> Domain -> Data 의 구조를 가지며, <br>
Dagger hilt를 통한 DI를 활용하여 UI에서 필요한 비즈니스 로직을 호출할 수 있습니다.

## 🔫 Troubleshooting

### Curriculum Indicator가 기대하는 결과와 다른 문제

그래서 아래 코드와 같이
Text를 감싸는 Column의 높이(`textColumnHeight`)를 활용해 Line의 End 값을 정해줘서 동적으로 Line의 길이를 정해줘서
Line을 그려주는 로직을 작성 했습니다.

하지만 `textColumnHeight`의 값이 변하면서 Recomposition이 일어나서 Line을 다시 그릴 줄 알았지만 앱을 실제로 실행 시키면
textColumnHeight의 초기 값 0을 이용해서 Line을 그려서 기대하는 결과와 다르게 나오는 문제가 발생했습니다.

(실제로 프리뷰에서도 강제로 리빌드 해주어야지 기대하는 결과가 나왔습니다.)

```kotlin 
Canvas(
  modifier = Modifier
    .width(16.dp)
    .height(textColumnHeight)
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
...

Column(
  modifier = Modifier.onGloballyPositioned { coordinates ->
    val newHeight = coordinates.size.height.toFloat()
    if (textColumnHeight != newHeight) {
      textColumnHeight = newHeight
    }
  }
) {
  ...
}

```

그래서 위와 같은 문제를 LocalDensity.current를 활용해 해결했습니다.

```kotlin 
Canvas(
  modifier = Modifier
    .width(16.dp)
    .height(with(LocalDensity.current) { textColumnHeight.toDp() })
) {
  ...
}
```
문제의 원인은 추후에 더 자세히 알아 보겠습니다.

## 📕 사용한 라이브러리
- Jetpack Compose
  -> 명령형 UI 라이브러리
- Dagger hilt
  -> 의존성 주입 라이브러리
- Retrofit2
  -> API 통신을 위한 네트워크 라이브러리
- Flow
  -> 비동기 데이터 흐름을 관리하기 위한 라이브러리
- Coil
  -> 이미지 로드 라이브러리
- Room
  -> 수강 정보를 저장하기 위한 라이브러리
- lottie
  -> lottie를 보여주기 위한 라이브러리
- Commonmark
  -> Markdown 파싱 라이브러리