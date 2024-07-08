package com.umc.playkuround.data

object Chapter {
    var value = 1

    fun nextChapter() {
        value++
    }

    fun getChapter() : String {
        return when(this.value) {
            1 -> "CH. 1"
            2 -> "CH. 2"
            3 -> "CH. 3"
            4 -> "CH. 4"
            5 -> "CH. 5"
            else -> "챕터 정보 없음"
        }
    }

    fun getTitle() : String {
        return when(this.value) {
            1 -> "일감호에서 살아남기"
            2 -> "홍예교 벽돌깨기"
            3 -> "수강신청 All 클릭"
            4 -> "틱택톡"
            5 -> "새천년관 달깨기"
            else -> "챕터 정보 없음"
        }
    }

    fun getGameRule() : String {
        return when(this.value) {
            1 -> "핸드폰을 기울여 사방에서 날라오는 장애물로부터 덕쿠를 보호해주세요!"
            2 -> "공을 튕겨 화면에 있는 홍예교의 모든 벽돌을 없애 주세요!"
            3 -> "하늘에서 내려오는 교과목들을 타이핑하여 성공적으로 수강신청을 해주세요!"
            4 -> "지혜의 서와의 대결에서 한 줄의 빙고를 먼저 만들어 주세요!"
            5 -> "새천년관 달을 깨주세요!"
            else -> "게임 룰 정보 없음"
        }
    }

    fun getClearCondition() : String {
        return when(this.value) {
            1 -> "150점 이상"
            2 -> "모든 벽돌 제거"
            3 -> "100점 이상"
            4 -> "빙고 완성"
            5 -> "새천년관 달을 100번 클릭"
            else -> "클리어 조건 정보 없음"
        }
    }

}