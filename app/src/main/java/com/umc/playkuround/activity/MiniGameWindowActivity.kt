package com.umc.playkuround.activity;

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.R
import com.umc.playkuround.dialog.GameOverDialog
import com.umc.playkuround.dialog.WaitingDialog

class MiniGameWindowActivity : AppCompatActivity() {
    private var clickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minigame_window)

        val button: Button = findViewById(R.id.activity_new_five_ic)

        button.setOnClickListener {
            clickCount++

            when (clickCount) {
                5 -> {
                    button.setBackgroundResource(R.drawable.new_five_four_ic)
                    showGameOverDialog(true)
                }
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
        fun showGameOverDialog(result: Int) {
            val gameOverDialog = GameOverDialog(this@MiniGameWindowActivity)
            gameOverDialog.setOnDismissListener {
//                val resultIntent = Intent()
//                resultIntent.putExtra("isNewLandmark", intent.getBooleanExtra("isNewLandmark", false))
//                setResult(result, resultIntent)
                this.finish()
            }

            //gameOverDialog.setInfo("FAIL!", false)
            gameOverDialog.setInfo("CLEAR!", true) // TODO
            println("Game Over Dialog")
            gameOverDialog.show()
        }

        val waitingDialog = WaitingDialog(this)
        waitingDialog.setOnFinishListener(object : WaitingDialog.OnFinishListener {
            override fun onFinish() {
                waitingDialog.dismiss()
                showGameOverDialog(Activity.RESULT_OK)
            }
        })
        waitingDialog.show()
    }
}
