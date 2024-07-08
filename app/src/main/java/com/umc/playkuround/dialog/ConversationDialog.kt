package com.umc.playkuround.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.umc.playkuround.R
import com.umc.playkuround.activity.OutroActivity
import com.umc.playkuround.data.Chapter
import com.umc.playkuround.data.Conversation
import com.umc.playkuround.data.ConversationManager
import com.umc.playkuround.data.Name
import com.umc.playkuround.databinding.DialogConversationBinding


class ConversationDialog(context: Context, chapter: Int) : Dialog(context) {

    val halfColor = ColorMatrix()
    val fullColor = ColorMatrix()

    private val conversationList: List<Conversation> =
        ConversationManager.getConversationList(chapter)
    private var idx: Int = 0

    private lateinit var binding: DialogConversationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogConversationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setDimAmount(0.6f)

        halfColor.setScale(0.5f, 0.5f, 0.5f, 1f)
        fullColor.setScale(1f, 1f, 1f, 1f)
        if (Name.character == 2) {
            binding.imgMan.setImageResource(R.drawable.conversation_woman)
        }

        updateConversation()

        binding.root.setOnClickListener {
            if (Chapter.value == 6 && idx == conversationList.size) {
                binding.imgMan.colorFilter = ColorMatrixColorFilter(halfColor)
                binding.imgDuck.colorFilter = ColorMatrixColorFilter(halfColor)
                binding.imgLast.visibility = View.VISIBLE
                idx++
            } else if (Chapter.value == 6 && idx > conversationList.size) {
                val intent = Intent(context, OutroActivity::class.java)
                context.startActivity(intent)
                dismiss()
            } else if (idx < conversationList.size) {
                updateConversation()
            } else {
                this.dismiss()
            }
        }
    }

    private fun updateConversation() {
        binding.tvContent.text = conversationList[idx].content
        if (conversationList[idx].isUser) {
            binding.tvName.text = Name.name
            binding.imgMan.colorFilter = ColorMatrixColorFilter(fullColor)
            binding.imgDuck.colorFilter = ColorMatrixColorFilter(halfColor)
        } else {
            binding.tvName.text = "덕쿠"
            binding.imgMan.colorFilter = ColorMatrixColorFilter(halfColor)
            binding.imgDuck.colorFilter = ColorMatrixColorFilter(fullColor)
        }
        idx++
    }

}