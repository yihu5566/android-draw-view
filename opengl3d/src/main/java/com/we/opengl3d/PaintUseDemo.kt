package com.we.opengl3d

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @Author : dongfang
 * @Created Time : 2022-09-26  11:07
 * @Description:
 */
class PaintUseDemo constructor(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val mPaint = Paint()
    private var mWidth: Int = 0
    private var mHeight: Int = 0

    init {
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 20f

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = w

    }


    override fun onDraw(canvas: Canvas?) {
        val rectF = RectF(0f, 0f, mWidth.toFloat(), mHeight.toFloat())
        canvas?.drawRect(rectF, mPaint)
//        mPaint.strokeCap = Paint.Cap.ROUND
//        mPaint.strokeJoin = Paint.Join.ROUND
//
//        canvas?.drawLine(0f, 0f, 100f, 100f, mPaint)
//        val rectSmall = RectF(100f, 100f, mWidth/2.toFloat(), mHeight/2.toFloat())
//
//        canvas?.drawRect(rectSmall,mPaint)
//        绘制路径
        val path = Path()
        path.moveTo(100f, 500f)
        path.lineTo(350f, 100f)
        path.lineTo(600f, 500f)
        mPaint.pathEffect = CornerPathEffect(130f)
        canvas?.drawPath(path, mPaint)


    }
}