package com.umc.playkuround.activity

import android.content.Context
import android.graphics.Canvas
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.umc.playkuround.R
import java.util.Timer
import java.util.TimerTask

class BreakOutView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var timer: Timer? = null
    private var handler = Handler(Looper.getMainLooper())
    private var isPause = true
    private var isFinish = false
    private var isFail = false

    interface OnFinishListener {
        fun onFinish(isFail: Boolean)
    }

    private var onFinishListener: OnFinishListener? = null

    fun setOnFinishListener(listener: OnFinishListener) {
        onFinishListener = listener
    }

    fun start() {
        isPause = false
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    ball.update()
                    for (brick in bricks) {
                        brick.update()
                    }
                    isBricksLeft()
                    if (isFinish) {
                        pause()
                        onFinishListener?.onFinish(isFail)
                    }
                    invalidate()
                }
            }
        }, 0, 3)
    }

    fun pause() {
        isPause = true
        timer?.cancel()
    }

    private val paddle: Paddle = Paddle()
    private val ball: Ball = Ball()
    private val bricks = ArrayList<Brick>()
    private val numBricks = 25

    init {
        for (i in 0 until numBricks) {
            val x = dpToPx(79f).toInt() / 2 + ((i / 5) % 2) * 50 + (i % 5) * dpToPx(79f).toInt()
            val y = 100 + (i / 5) * dpToPx(28f).toInt()
            bricks.add(Brick(x, y))
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        paddle.x = width / 2
        paddle.y = (height / 1.3).toInt()
        ball.x = width / 5
        ball.y = (height / 2).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paddle.draw(canvas)
        ball.draw(canvas)
        for (brick in bricks) {
            if (brick?.isVisible == true) {
                brick.draw(canvas)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                paddle.paddleOffset = (event.x - paddle.x).toInt()
            }

            MotionEvent.ACTION_MOVE -> {
                paddle.update(event.x - paddle.paddleOffset)
            }
        }
        return true
    }

    private fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        )
    }

    private fun isBricksLeft() {
        if (isFinish) return
        for (brick in bricks) {
            if (brick.isVisible) {
                isFinish = false
                return
            }
        }
        isFinish = true
    }

    private inner class Paddle {
        private var paddleImg = ContextCompat.getDrawable(context, R.drawable.bar)
        var paddleOffset = 0
        val width: Int = dpToPx(100f).toInt()
        val height: Int = dpToPx(16f).toInt()
        var x: Int
        var y: Int

        init {
            x = (this.width / 2 - width / 2).toFloat().toInt()
            y = this.height * 5 / 4
        }

        fun draw(canvas: Canvas) {
            paddleImg?.let {
                it.setBounds(
                    (x - width / 2), (y - height / 2),
                    (x + width / 2), (y + height / 2)
                )
                it.draw(canvas)
            }
        }

        fun update(newX: Float) {
            if (newX < width / 2 || newX > this@BreakOutView.width - width / 2) return
            x = (newX).toInt()
        }
    }

    private inner class Ball {
        private var ballImg = ContextCompat.getDrawable(context, R.drawable.ball)
        val width: Int = dpToPx(32f).toInt()
        val height: Int = dpToPx(32f).toInt()
        var x: Int
        var y: Int
        var xVelocity: Float
        var yVelocity: Float

        init {
            x = (this.width / 2).toFloat().toInt()
            y = (this.height / 2).toFloat().toInt()
            xVelocity = 2f
            yVelocity = 2f
        }

        fun draw(canvas: Canvas) {
            ballImg?.let {
                it.setBounds(
                    (x - width / 2), (y - height / 2),
                    (x + width / 2), (y + height / 2)
                )
                it.draw(canvas)
            }
        }

        fun update() {
            x = (x + xVelocity).toInt()
            y = (y + yVelocity).toInt()
            if (x < width / 2 || x > this@BreakOutView.width - width / 2) {
                xVelocity = -xVelocity
            }
            if (y < height / 2) {
                yVelocity = -yVelocity
            }
            if (y > this@BreakOutView.height - height / 2) {
                isFinish = true
                isFail = true
            }
            if (!(y > paddle.y + paddle.height / 2) && y > paddle.y - paddle.height && x > paddle.x - paddle.width / 2 && x < paddle.x + paddle.width) {
                yVelocity = -yVelocity
            }
        }
    }

    private inner class Brick(x: Int, y: Int) {
        private var brickImg = ContextCompat.getDrawable(context, R.drawable.brick)
        var isVisible: Boolean = true

        val width: Int = dpToPx(79f).toInt()
        val height: Int = dpToPx(28f).toInt()
        var x: Int = x
        var y: Int = y

        fun draw(canvas: Canvas) {
            brickImg?.let {
                it.setBounds(
                    (x - width / 2), (y - height / 2),
                    (x + width / 2), (y + height / 2)
                )
                it.draw(canvas)
            }
        }

        fun update() {
            if (isVisible && ball.x > x - width / 2 && ball.x < x + width / 2 && ball.y < y + height) {
                isVisible = false
                ball.yVelocity = -1 * ball.yVelocity
            }
        }
    }

}