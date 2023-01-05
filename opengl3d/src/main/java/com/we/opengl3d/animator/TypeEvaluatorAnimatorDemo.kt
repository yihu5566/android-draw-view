package com.we.opengl3d.animator

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.we.opengl3d.R

/**
 * @Author : dongfang
 * @Created Time : 2022-09-28  09:30
 * @Description:
 */
class TypeEvaluatorAnimatorDemo constructor(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    var progress: PointF = PointF(0f, 0f)
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    val mPaint = Paint()
    var mRectF = RectF()
    val mBitmap = BitmapFactory.decodeResource(context?.resources, R.drawable.time)

    init {
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.FILL
        mPaint.strokeCap = Paint.Cap.ROUND
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mRectF = RectF(40f, 40f, w.toFloat() - 40f, w.toFloat() - 40f)
    }

    override fun onDraw(canvas: Canvas) {
//        canvas?.drawArc(mRectF, 135f, progress * 2.7f, false, mPaint)
        Log.d("progress", "---$progress--")
        val mMatrix = Matrix()
        canvas.setMatrix(mMatrix)
        canvas.drawBitmap(mBitmap, mMatrix, mPaint)
    }

}