package com.umc.playkuround.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.data.Name
import com.umc.playkuround.databinding.ActivityConfirmNameBinding

class ConfirmNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmNameBinding.inflate(layoutInflater)

        binding.tvConfirmName.text = "당신의 이름이\n'" + Name.name + "'이(가) 맞나요?"
        setContentView(binding.root)

        binding.btnYes.setOnClickListener {
            val intent = Intent(this, ChooseCharacterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnNo.setOnClickListener {
            val intent = Intent(this, InputNameActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}