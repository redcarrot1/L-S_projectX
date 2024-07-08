package com.umc.playkuround.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.R
import com.umc.playkuround.databinding.ActivityLoginBinding
import java.util.Timer
import kotlin.concurrent.timer

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private var bgGif: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            bgGif?.cancel()

            val intent = Intent(this, InputNameActivity::class.java)
            startActivity(intent)
            finish()
        }

        var num = 0
        bgGif = timer(period = 300) {
            runOnUiThread {
                if (num % 4 == 0) binding.loginBackgroundIv.setImageResource(R.drawable.login_bg01)
                else if (num % 4 == 1) binding.loginBackgroundIv.setImageResource(R.drawable.login_bg02)
                else if (num % 4 == 2) binding.loginBackgroundIv.setImageResource(R.drawable.login_bg03)
                else if (num % 4 == 3) binding.loginBackgroundIv.setImageResource(R.drawable.login_bg04)
                num++
            }
        }
    }

}