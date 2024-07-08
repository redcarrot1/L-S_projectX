package com.umc.playkuround.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.data.Name
import com.umc.playkuround.databinding.ActivityInputNameBinding

class InputNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btNameComplete.setOnClickListener {
            Name.name = binding.etName.text.toString()
            val intent = Intent(this, ConfirmNameActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}