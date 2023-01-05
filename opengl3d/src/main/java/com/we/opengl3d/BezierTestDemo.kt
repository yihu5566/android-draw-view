package com.we.opengl3d

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @Author : dongfang
 * @Created Time : 2022-09-26  17:02
 * @Description:
 */
class BezierTestDemo constructor(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    val mPaint = Paint()
    val mAuxiliaryPaint = Paint()
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    val path: Path = Path()

    var startX: Float = 0f
    var startY: Float = 0f
    var endX: Float = 0f
    var endY: Float = 0f

    var mAuxiliaryX: Float = 300f
    var mAuxiliaryY: Float = 100f
    var mAuxiliaryTwoX: Float = 0f
    var mAuxiliaryTwoY: Float = 0f


    init {
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10f
        mPaint.strokeCap = Paint.Cap.ROUND

        mAuxiliaryPaint.set(mPaint)
        mAuxiliaryPaint.color = Color.BLACK

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = w

        startX = (w / 4).toFloat()
        startY = (h / 2 - 200).toFloat()

        endX = (w / 4 * 3).toFloat()
        endY = (h / 2 - 200).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        path.reset()
        path.moveTo(startX, startY)

        canvas?.drawPoint(mAuxiliaryX, mAuxiliaryY, mPaint)
        canvas?.drawLine(startX, startY, mAuxiliaryX, mAuxiliaryY, mAuxiliaryPaint)
        canvas?.drawLine(endX, endY, mAuxiliaryX, mAuxiliaryY, mAuxiliaryPaint)

        canvas?.drawLine(startX, startY, mAuxiliaryTwoX, mAuxiliaryTwoY, mAuxiliaryPaint)
        canvas?.drawLine(endX, endY, mAuxiliaryTwoX, mAuxiliaryTwoY, mAuxiliaryPaint)
        path.cubicTo(mAuxiliaryX, mAuxiliaryY, mAuxiliaryTwoX, mAuxiliaryTwoY, endX, endY)
        canvas?.drawPath(path, mPaint)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (event.x - mAuxiliaryTwoX < 50) {
                    mAuxiliaryTwoX = event.x
                    mAuxiliaryTwoY = event.y
                } else {
                    mAuxiliaryX = event.x
                    mAuxiliaryY = event.y
                }


            }
            MotionEvent.ACTION_MOVE -> {
                if (event.x - mAuxiliaryTwoX < 50) {
                    mAuxiliaryTwoX = event.x
                    mAuxiliaryTwoY = event.y
                } else {
                    mAuxiliaryX = event.x
                    mAuxiliaryY = event.y
                }
            }
            else -> {

            }
        }
        postInvalidate()
        return true
    }
}