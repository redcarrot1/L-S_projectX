package com.umc.playkuround.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.data.Name
import com.umc.playkuround.databinding.ActivityChooseCharacterBinding

class ChooseCharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseCharacterBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMan.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnWoman.setOnClickListener {
            Name.character = 2
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}