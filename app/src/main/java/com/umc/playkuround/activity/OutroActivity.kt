package com.umc.playkuround.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.databinding.ActivityOutroBinding

class OutroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            finishAffinity()
            System.exit(0)
        }
    }

}