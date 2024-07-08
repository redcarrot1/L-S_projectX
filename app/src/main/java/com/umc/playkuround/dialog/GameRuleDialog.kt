package com.umc.playkuround.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.umc.playkuround.activity.MiniGameAvoidActivity
import com.umc.playkuround.activity.MiniGameBreakOutActivity
import com.umc.playkuround.activity.MiniGameTicTacToeActivity
import com.umc.playkuround.activity.MiniGameTypingActivity
import com.umc.playkuround.activity.MiniGameWindowActivity
import com.umc.playkuround.data.Chapter
import com.umc.playkuround.databinding.DialogGameRuleBinding

class GameRuleDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogGameRuleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogGameRuleBinding.inflate(layoutInflater)

        binding.tvChapter.text = Chapter.getChapter()
        binding.tvTitle.text = Chapter.getTitle()
        binding.tvGameRule.text = Chapter.getGameRule()
        binding.tvClearCondition.text = Chapter.getClearCondition()

        setContentView(binding.root)
        setCancelable(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setDimAmount(0.6f)

        binding.dialogGameStart.setOnClickListener {
            var intent: Intent? = null
            if (Chapter.value == 1) {
                // 일감호에서 살아남기
                intent = Intent(context, MiniGameAvoidActivity::class.java)
            } else if (Chapter.value == 2) {
                // 홍예교 벽돌깨기
                intent = Intent(context, MiniGameBreakOutActivity::class.java)
            } else if (Chapter.value == 3) {
                // 수강신청 올 클릭
                intent = Intent(context, MiniGameTypingActivity::class.java)
            } else if (Chapter.value == 4) {
                // 틱택톡
                intent = Intent(context, MiniGameTicTacToeActivity::class.java)
            } else if (Chapter.value == 5) {
                // 새천년관 창문깨기
                intent = Intent(context, MiniGameWindowActivity::class.java)
            }
            context.startActivity(intent)
            dismiss()
        }
    }

}