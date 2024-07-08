package com.umc.playkuround.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.umc.playkuround.R
import com.umc.playkuround.data.Chapter
import com.umc.playkuround.databinding.ActivityMap2Binding
import com.umc.playkuround.dialog.ConversationDialog
import com.umc.playkuround.dialog.GameRuleDialog

class Map2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMap2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMap2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            val gameRuleDialog = GameRuleDialog(this)
            gameRuleDialog.show()
        }
    }

    override fun onResume() {
        super.onResume()
        updateBackgroundImage()
        ConversationDialog(this, Chapter.value).show()
    }

    @SuppressLint("WrongViewCast")
    fun updateBackgroundImage() {
        val mv = findViewById<ImageView>(R.id.view_bg)
        when (Chapter.value) {
            1 -> Glide.with(this).load(R.drawable.map1).into(mv)
            2 -> Glide.with(this).load(R.drawable.map2).into(mv)
            3 -> Glide.with(this).load(R.drawable.map3).into(mv)
            4 -> Glide.with(this).load(R.drawable.map4).into(mv)
            5 -> Glide.with(this).load(R.drawable.map5).into(mv)
        }
    }

    private fun dimBackground() {
        val params = window.attributes
        params.flags = params.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
        params.dimAmount = 0.5f
        window.attributes = params
    }

    private fun restoreBackground() {
        val params = window.attributes
        params.flags = params.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
        window.attributes = params
    }

}