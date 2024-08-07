package com.umc.playkuround.activity

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.R
import com.umc.playkuround.custom_view.TextRainView
import com.umc.playkuround.databinding.ActivityMinigameTypingBinding
import com.umc.playkuround.dialog.CountdownDialog
import com.umc.playkuround.dialog.GameOverDialog
import com.umc.playkuround.dialog.WaitingDialog
import com.umc.playkuround.util.SoundPlayer


class MiniGameTypingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMinigameTypingBinding
    private var life = 3
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMinigameTypingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView2.setOnClickListener {
            binding.typingTextRainView.pause()
            showGameOverDialog(true)
        }

        binding.typingTextBox.requestFocus()
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.typingTextBox, InputMethodManager.SHOW_IMPLICIT)

        binding.typingTextRainView.setOnTextRainDropListener(object :
            TextRainView.OnTextRainDropListener {
            override fun drop() {
                life--
                when (life) {
                    2 -> binding.typingLife1Iv.setImageResource(R.drawable.typing_empty_heart)
                    1 -> binding.typingLife2Iv.setImageResource(R.drawable.typing_empty_heart)
                    0 -> {
                        binding.typingLife3Iv.setImageResource(R.drawable.typing_empty_heart)
                        binding.typingTextRainView.pause()
                        showGameOverDialog(false)
                    }
                }
            }
        })

        binding.typingTextBox.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                if (binding.typingTextRainView.deleteText(binding.typingTextBox.text.toString())) {
                    SoundPlayer(applicationContext, R.raw.typing_correct).play()
                    score += if (binding.typingTextBox.text.length <= 4) 2
                    else if (binding.typingTextBox.text.length <= 8) 4
                    else 6

                    if (binding.typingTextBox.text.toString() == "녹색지대")
                        score += 3

                    binding.typingScoreTv.text = score.toString()
                }
                binding.typingTextBox.text.clear()
                //showGameOverDialog() // TODO
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        binding.typingTextRainView.bringToFront()
        binding.typingTextBox.bringToFront()

        val countdownDialog = CountdownDialog(this)
        countdownDialog.setOnFinishListener(object : CountdownDialog.OnFinishListener {
            override fun onFinish() {
                binding.typingTextRainView.start()
            }
        })
        countdownDialog.show()
    }

    private fun showGameOverDialog(debugMode: Boolean) {
        fun showGameOverDialog() {
            val gameOverDialog = GameOverDialog(this@MiniGameTypingActivity)
            gameOverDialog.setOnDismissListener {
                this.finish()
            }

            if (debugMode) {
                gameOverDialog.setInfo(score.toString() + "점", true)
            } else {
                var isClear = false
                if (score >= 100) isClear = true
                gameOverDialog.setInfo(score.toString() + "점", isClear)
            }
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