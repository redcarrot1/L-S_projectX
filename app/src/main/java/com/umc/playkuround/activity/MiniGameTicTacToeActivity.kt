package com.umc.playkuround.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.umc.playkuround.R
import com.umc.playkuround.databinding.ActivityMinigameTicTacToeBinding
import com.umc.playkuround.dialog.GameOverDialog
import com.umc.playkuround.dialog.WaitingDialog
import kotlin.random.Random

class MiniGameTicTacToeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMinigameTicTacToeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMinigameTicTacToeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun buClick(view: View) {
        val buSelected = view as Button
        var cellID = 0
        when (buSelected.id) {
            R.id.bu1 -> cellID = 1
            R.id.bu2 -> cellID = 2
            R.id.bu3 -> cellID = 3
            R.id.bu4 -> cellID = 4
            R.id.bu5 -> cellID = 5
            R.id.bu6 -> cellID = 6
            R.id.bu7 -> cellID = 7
            R.id.bu8 -> cellID = 8
            R.id.bu9 -> cellID = 9
        }
        playGame(cellID, buSelected)
    }

    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()

    private var activePlayer = 1

    private fun playGame(cellID: Int, buSelected: Button) {
        if (activePlayer == 1) {
            buSelected.setBackgroundResource(R.drawable.yesyesyes)
            buSelected.backgroundTintList = null
            player1.add(cellID)
            activePlayer = 2
        } else {
            buSelected.setBackgroundResource(R.drawable.nonono)
            buSelected.backgroundTintList = null
            player2.add(cellID)
            activePlayer = 1
        }

        val winner = checkWinner()
        if (winner != -1) {
            if (winner == 1) {
                showGameOverDialog(true)
            } else {
                showGameOverDialog(false)
            }
        } else {
            if (player1.size + player2.size == 9) {
                showGameOverDialog(false)
            } else if (activePlayer == 2) {
                AutoPlay()
            }
        }

        buSelected.isEnabled = false
    }

    private fun checkWinner(): Int {
        var winner = -1
        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        // row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        // row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        // col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        // col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        // col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        // diagonal
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }
        return winner
    }

    private fun AutoPlay() {
        val emptyCells = ArrayList<Int>()
        for (cellID in 1..9) {
            if (!(player1.contains(cellID) || player2.contains(cellID))) {
                emptyCells.add(cellID)
            }
        }

        val r = Random.Default
        val randIndex = r.nextInt(emptyCells.size)
        val cellID = emptyCells[randIndex]
        val buSelected = when (cellID) {
            1 -> binding.bu1
            2 -> binding.bu2
            3 -> binding.bu3
            4 -> binding.bu4
            5 -> binding.bu5
            6 -> binding.bu6
            7 -> binding.bu7
            8 -> binding.bu8
            9 -> binding.bu9
            else -> binding.bu1
        }

        playGame(cellID, buSelected)
    }

    private fun showGameOverDialog(isSuccess: Boolean) {
        fun showGameOverDialog() {
            val gameOverDialog = GameOverDialog(this@MiniGameTicTacToeActivity)
            gameOverDialog.setOnDismissListener {
                this.finish()
            }

            if (isSuccess) {
                gameOverDialog.setInfo("CLEAR!", true)
            } else {
                gameOverDialog.setInfo("FAIL!", false)
            }
            //gameOverDialog.setInfo("CLEAR!", true) // TODO
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