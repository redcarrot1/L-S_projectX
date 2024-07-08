package com.umc.playkuround.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.data.Name
import com.umc.playkuround.databinding.ActivityInputNameBinding
import com.umc.playkuround.databinding.ActivityIntroBinding
import com.umc.playkuround.databinding.ActivityTestGameBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tv1.setText("내 이름은 " + Name.name)

        var cnt = 1
        binding.root.setOnClickListener {
            if (cnt == 1) {
                binding.tv2.setText("오늘은 건국대학교 캠퍼스 투어가 있는 날..")
            } else if (cnt == 2) {
                binding.tv3.setText("너무 긴장한 나머지")
            } else if (cnt == 3) {
                binding.tv4.setText("잠을 제대로 못 자고")
            } else if (cnt == 4) {
                binding.tv5.setText("지각을 하고 말았다..")
            } else if (cnt == 5) {
                binding.tv6.setText("나는 약속 장소인 학생회관으로 뛰어가다 눈 앞에 이상한 물체를 보게 되는데..")
            }
            else{
                val intent = Intent(this, Map2Activity::class.java)
                startActivity(intent)
                finish()
            }
            cnt++
        }

    }

}