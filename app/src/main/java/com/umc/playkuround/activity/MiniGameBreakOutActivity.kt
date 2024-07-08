package com.umc.playkuround.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.projectx.breakout.BreakOutView
import com.umc.playkuround.R
import com.umc.playkuround.dialog.GameOverDialog
import com.umc.playkuround.dialog.WaitingDialog
import com.umc.playkuround.util.SoundPlayer

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

        fun showGameOverDialog(result: Int) {
            val gameOverDialog = GameOverDialog(this@MiniGameBreakOutActivity)
            gameOverDialog.setOnDismissListener {
//                val resultIntent = Intent()
//                resultIntent.putExtra(
//                    "isNewLandmark",
//                    intent.getBooleanExtra("isNewLandmark", false)
//                )
//                setResult(result, resultIntent)
                this.finish()
            }
            gameOverDialog.setInfo("CLEAR!", true)
            //gameOverDialog.setInfo("FAIL!", false)
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