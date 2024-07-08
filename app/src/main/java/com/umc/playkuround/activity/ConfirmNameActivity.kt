package com.umc.playkuround.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.data.Name
import com.umc.playkuround.databinding.ActivityConfirmNameBinding

class ConfirmNameActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfirmNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmNameBinding.inflate(layoutInflater)

        binding.textView.setText("당신의 이름이 "+Name.name+"이(가) 맞습니까?")
        setContentView(binding.root)

        binding.btYes.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btNo.setOnClickListener {
            val intent = Intent(this, InputNameActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}