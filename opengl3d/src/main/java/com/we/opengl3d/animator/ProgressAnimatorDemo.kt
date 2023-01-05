package com.we.opengl3d.animator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @Author : dongfang
 * @Created Time : 2022-09-28  09:30
 * @Description:
 */
class ProgressAnimatorDemo constructor(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    var progress: Float = 0f
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    val mPaint = Paint()
    var mRectF = RectF()

    init {
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 40f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mRectF = RectF(40f, 40f, w.toFloat() - 40f, w.toFloat() - 40f)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawArc(mRectF, 135f, progress * 2.7f, false, mPaint)

        Log.d("progress", "---$progress--")
    }

}