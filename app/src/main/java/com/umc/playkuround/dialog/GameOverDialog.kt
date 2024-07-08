package com.umc.playkuround.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import com.umc.playkuround.R
import com.umc.playkuround.data.Chapter
import com.umc.playkuround.databinding.DialogGameOverBinding
import com.umc.playkuround.util.SoundPlayer

class GameOverDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogGameOverBinding
    private var message = ""
    private var isClear = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogGameOverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCancelable(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setDimAmount(0.6f)

        if (isClear) {
            binding.tvResult.text = "성공"
        } else {
            binding.tvResult.text = "실패"
        }

        binding.dialogGameOverGameNameTv.text = Chapter.getTitle()
        binding.tvScore.text = message
        binding.tvClearCondition.text = Chapter.getClearCondition()

        val quitBtn = findViewById<Button>(R.id.dialog_game_over_quit)
        quitBtn.setOnClickListener {
            //SoundPlayer(context, R.raw.button_click_sound).play()
            if (isClear) {
                Chapter.nextChapter()
            }
            this.dismiss()
        }
    }

    fun setInfo(message: String, isClear: Boolean) {
        this.message = message
        this.isClear = isClear
    }

}