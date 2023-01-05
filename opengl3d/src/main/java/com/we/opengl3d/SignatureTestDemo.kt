package com.we.opengl3d

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @Author : dongfang
 * @Created Time : 2022-09-26  11:07
 * @Description:
 */
class SignatureTestDemo constructor(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    val mPaint = Paint()
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    val path: Path = Path()

    init {
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 20f
        mPaint.strokeCap = Paint.Cap.ROUND

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = w

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                path.reset()
                path.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)

            }
            else -> {

            }
        }
        postInvalidate()
        return true
    }


    override fun onDraw(canvas: Canvas?) {
        canvas?.save()
        canvas?.translate((mWidth / 2).toFloat(), (mHeight / 2).toFloat())
        val rectF = RectF(
            -(mWidth / 2).toFloat(),
            -(mHeight / 2).toFloat(),
            (mWidth / 2).toFloat(),
            (mHeight / 2).toFloat()
        )
        canvas?.drawRect(rectF, mPaint)
        canvas?.restore()

        canvas?.save()
        mPaint.pathEffect = CornerPathEffect(130f)
        canvas?.drawPath(path, mPaint)
        canvas?.restore()


    }
}