package com.umc.playkuround.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.umc.playkuround.R
import com.umc.playkuround.dialog.GameOverDialog
import com.umc.playkuround.dialog.WaitingDialog
import com.umc.playkuround.util.SoundPlayer
import kotlin.random.Random

class MiniGameTicTacToeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_minigame_tic_tac_toe)
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
        PlayGame(cellID, buSelected)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    var activePlayer = 1

    private fun PlayGame(cellID: Int, buSelected: Button) {
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
                println("Player 1 win the game")
                showGameOverDialog(true)
                //Toast.makeText(this, " Player 1  win the game", Toast.LENGTH_LONG).show()
            } else {
                println("Player 2 win the game")
                showGameOverDialog(false)
                //Toast.makeText(this, " Player 2  win the game", Toast.LENGTH_LONG).show()
            }
        } else {
            if (activePlayer == 2) {
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

        var buSelected: Button
        when (cellID) {
            1 -> buSelected = findViewById(R.id.bu1)
            2 -> buSelected = findViewById(R.id.bu2)
            3 -> buSelected = findViewById(R.id.bu3)
            4 -> buSelected = findViewById(R.id.bu4)
            5 -> buSelected = findViewById(R.id.bu5)
            6 -> buSelected = findViewById(R.id.bu6)
            7 -> buSelected = findViewById(R.id.bu7)
            8 -> buSelected = findViewById(R.id.bu8)
            9 -> buSelected = findViewById(R.id.bu9)
            else -> buSelected = findViewById(R.id.bu1)
        }

        PlayGame(cellID, buSelected)
    }

    private fun showGameOverDialog(isSuccess: Boolean) {
        fun showGameOverDialog(result: Int) {
            val gameOverDialog = GameOverDialog(this@MiniGameTicTacToeActivity)
            gameOverDialog.setOnDismissListener {
//                val resultIntent = Intent()
//                resultIntent.putExtra("isNewLandmark", intent.getBooleanExtra("isNewLandmark", false))
//                setResult(result, resultIntent)
                this.finish()
            }

            //gameOverDialog.setInfo("FAIL!", false)
            gameOverDialog.setInfo("CLEAR!", true) // TODO
            println("Game Over Dialog")
            gameOverDialog.show()
        }

        val waitingDialog = WaitingDialog(this)
        waitingDialog.setOnFinishListener(object : WaitingDialog.OnFinishListener {
            override fun onFinish() {
                waitingDialog.dismiss()
                showGameOverDialog(Activity.RESULT_OK)
            }
        })
        waitingDialog.show()
    }
}