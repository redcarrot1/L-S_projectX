package com.umc.playkuround.activity;

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.R
import com.umc.playkuround.dialog.GameOverDialog
import com.umc.playkuround.dialog.WaitingDialog

class MiniGameWindowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minigame_window)

        val button: Button = findViewById(R.id.activity_new_five_ic)
        var clickCount = 0

        button.setOnClickListener {
            clickCount++

            when (clickCount) {
                5 -> {
                    button.setBackgroundResource(R.drawable.new_five_four_ic)
                    showGameOverDialog(true)
                } // TODO
//                30 -> button.setBackgroundResource(R.drawable.new_five_two_ic)
//                60 -> button.setBackgroundResource(R.drawable.new_five_three_ic)
//                100 -> {
//                    button.setBackgroundResource(R.drawable.new_five_four_ic)
//                    showGameOverDialog(true)
//                }
            }
        }
    }

    private fun showGameOverDialog(isSuccess: Boolean) {
        fun showGameOverDialog() {
            val gameOverDialog = GameOverDialog(this@MiniGameWindowActivity)
            gameOverDialog.setOnDismissListener {
                this.finish()
            }

//            if (isSuccess) {
//                gameOverDialog.setInfo("CLEAR!", true)
//            } else {
//                gameOverDialog.setInfo("FAIL!", false)
//            }
            gameOverDialog.setInfo("CLEAR!", true) // TODO
            gameOverDialog.show()
        }

        val waitingDialog = WaitingDialog(this)
        waitingDialog.setOnFinishListener(object : WaitingDialog.OnFinishListener {
            override fun onFinish() {
                waitingDialog.dismiss()
                showGameOverDialog()
            }
        })
        waitingDialog.show()
    }
}
