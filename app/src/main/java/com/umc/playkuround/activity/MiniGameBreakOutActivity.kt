package com.umc.playkuround.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.R
import com.umc.playkuround.dialog.GameOverDialog
import com.umc.playkuround.dialog.WaitingDialog

class MiniGameBreakOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minigame_break_out)

        val breakOutView = findViewById<BreakOutView>(R.id.break_out_view)
        breakOutView.setOnFinishListener(object : BreakOutView.OnFinishListener {
            override fun onFinish(isFail: Boolean) {
                showGameOverDialog(!isFail)
            }
        })
        breakOutView.start()
    }

    private fun showGameOverDialog(isSuccess: Boolean) {
        // SoundPlayer(applicationContext, R.raw.avoid_game_over).play()

        fun showGameOverDialog() {
            val gameOverDialog = GameOverDialog(this@MiniGameBreakOutActivity)
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