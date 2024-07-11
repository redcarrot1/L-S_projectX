package com.umc.playkuround.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.custom_view.BreakOutView
import com.umc.playkuround.databinding.ActivityMinigameBreakOutBinding
import com.umc.playkuround.dialog.GameOverDialog
import com.umc.playkuround.dialog.WaitingDialog

class MiniGameBreakOutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMinigameBreakOutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMinigameBreakOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.breakOutTitleTv.setOnClickListener {
            binding.breakOutView.pause()
            showGameOverDialog(true)
        }

        binding.breakOutView.setOnFinishListener(object : BreakOutView.OnFinishListener {
            override fun onFinish(isFail: Boolean) {
                showGameOverDialog(!isFail)
            }
        })
        binding.breakOutView.start()
    }

    private fun showGameOverDialog(isSuccess: Boolean) {

        fun showGameOverDialog() {
            val gameOverDialog = GameOverDialog(this@MiniGameBreakOutActivity)
            gameOverDialog.setOnDismissListener {
                this.finish()
            }

            if (isSuccess) {
                gameOverDialog.setInfo("CLEAR!", true)
            } else {
                gameOverDialog.setInfo("FAIL!", false)
            }
            //gameOverDialog.setInfo("CLEAR!", true) // TODO
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