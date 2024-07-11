package com.umc.playkuround.activity;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.R
import com.umc.playkuround.databinding.ActivityMinigameWindowBinding
import com.umc.playkuround.dialog.GameOverDialog
import com.umc.playkuround.dialog.WaitingDialog

class MiniGameWindowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMinigameWindowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMinigameWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView2.setOnClickListener {
            binding.activityNewFiveIc.setBackgroundResource(R.drawable.new_five_four_ic)
            showGameOverDialog(true)
        }

        var clickCount = 0
        binding.activityNewFiveIc.setOnClickListener {
            clickCount++

            when (clickCount) {
                30 -> binding.activityNewFiveIc.setBackgroundResource(R.drawable.new_five_two_ic)
                60 -> binding.activityNewFiveIc.setBackgroundResource(R.drawable.new_five_three_ic)
                100 -> {
                    binding.activityNewFiveIc.setBackgroundResource(R.drawable.new_five_four_ic)
                    showGameOverDialog(true)
                }
            }
        }
    }

    private fun showGameOverDialog(isSuccess: Boolean) {
        fun showGameOverDialog() {
            val gameOverDialog = GameOverDialog(this@MiniGameWindowActivity)
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
