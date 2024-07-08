package com.umc.playkuround.data

object ConversationManager {

    fun getConversationList(chapter: Int): List<Conversation> {
        return when (chapter) {
            1 -> listOf(
                Conversation(true, "너 정체가뭐야..?"),
                Conversation(false, "(헤롱헤롱) 나는 일감호에 사는 덕쿠라고해"),
                Conversation(false, "난 항상 일감호에서 학생들을 보며 나도 건대학생이 되고싶다는 꿈을 꿨어.. 그러다가 이렇게…."),
                Conversation(true, "오리가 건대학생이 될 수 있긴해?!"),
                Conversation(false, "건대에는 명예 오리전형이 있어 특별한 시험들을 통과 해야 하는데, 학교 건물들에 있는 과제들을 수행해야해"),
                Conversation(true, "하지만 나 혼자 해결하기에는 너무 어려운 과제야"),
                Conversation(false, "우리 이렇게 만난것도 인연인데 혹시 날 도와줄 수 있을까?"),
                Conversation(true, "응 도와줄게")
            )

            2 -> listOf(
                Conversation(true, "홍예교 진짜 이쁘다!"),
                Conversation(false, "혹시 홍예교의 전설 알아?"),
                Conversation(true, "그게 뭔데?"),
                Conversation(false, "홍예교를 좋아하는 사람이랑 걸으면 사랑이 이루어진다는거야!"),
                Conversation(true, "거짓말~~~"),
                Conversation(false, "근데 사귀는 사람들끼리 건너면 헤어진다고 하더라"),
                Conversation(true, "헉!!!"),
                Conversation(false, "여기서는 신기하게 공으로 벽돌을 깨는 미션이네.. 커플들 다 깨지라는 본심인가")
            )

            3 -> listOf(
                Conversation(false, "대학생이면 당연히 수강신청을 해야하는걸 왜 몰랐지!!"),
                Conversation(true, "너 손으로는 타자도 못치잖아"),
                Conversation(false, "그래도 수강바구니 제도가 있어서 클릭만 하면되는데"),
                Conversation(false, "1학년땐 직접 쳐야해 (절망)"),
                Conversation(false, "이번 한번만 도와줄 수 있을까?"),
                Conversation(true, "난 수강신청 한번도 안해봤는데 ㅠㅠ 일단해볼게!")
            )

            4 -> listOf(
                Conversation(
                    false,
                    "이젠 케이허브으로 가자! 건국대학교에는 자율 학습을 할 수 있는 케이허브랑 케이큐브가 있다고~ 위인전에서 예약 가능해!"
                ),
                Conversation(true, "(언제까지 데리고 다니는거야..) 응응"),
                Conversation(false, "...(파사삭덕쿠)"),
                Conversation(true, "왜그렇게 표정이 안좋아..?"),
                Conversation(false, "지혜의책..? 그걸 찾으라는데?")
            )

            5 -> listOf(
                Conversation(true, "덕쿠는 왜 건국대학교 학생이 되고싶어?"),
                Conversation(false, "항상 일감호에서 혼자 학생들을 바라보는게 내 일상이였어"),
                Conversation(false, "여기서 학교를 바라보면 참 많은걸 볼 수 있는데"),
                Conversation(
                    false,
                    "건국대학교 학생들이 웃으며 친구들과 캠퍼스를 다니고, 열정적으로 프로젝트도 진행하고, 청춘을 즐기는 모습을 보면서"
                ),
                Conversation(false, "건국대학교 학생이 되고 싶었던것 같아"),
                Conversation(true, "(생각보다 감성적이네) 오 그런 이유가 있었구나"),
                Conversation(false, "너무 감성적이었나 약간의 tmi를 알려주면 여기가 새천년관이잖아?"),
                Conversation(false, "새천년관 건물은 2000년도가 되면서 학교의 새로운 도약을 기념하기위해 만들어졌어 그리고 스마트폰 모양이야"),
                Conversation(true, "헐!"),
                Conversation(false, "드디어 마지막 미션이야! 클릭해서 달을 깨버리자!"),
            )

            6 -> listOf(
                Conversation(false, "고마워 내가 건대 학생이 될 수 있었던건 다 너 덕분이야"),
                Conversation(false, "내 행운을 너에게 줄게. 이젠 너 차례야!"),
                Conversation(true, "(합격 부적 사진) 건대합격 부적을 얻었다!")
            )

            else -> {
                listOf()
            }
        }
    }
}
