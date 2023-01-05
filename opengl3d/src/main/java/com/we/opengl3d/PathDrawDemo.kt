package com.we.opengl3d

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.provider.DocumentsContract
import android.util.AttributeSet
import android.view.View

/**
 * @Author : dongfang
 * @Created Time : 2022-09-22  10:56
 * @Description:
 */
class PathDrawDemo : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    var mWith: Int = 0
    var mHeight: Int = 0
    val mPaint: Paint

    init {
        mPaint = Paint()
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.FILL
        mPaint.strokeWidth = 10f

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWith = w
        mHeight = h
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.translate((mWith / 2).toFloat(), (mHeight / 2).toFloat())
        canvas?.drawLine(-(width / 2).toFloat(), 0f, (width / 2).toFloat(), 0f, mPaint)
        canvas?.drawLine(0f, -(mHeight / 2).toFloat(), 0f, (mHeight / 2).toFloat(), mPaint)

        mPaint.reset()
        mPaint.color = Color.BLACK
        canvas?.drawCircle(0f, 0f, 200f, mPaint)

        mPaint.reset()
        mPaint.color = Color.RED

        val path1 = Path()
        val path2 = Path()
        val path3 = Path()
        val path4 = Path()

        path1.addCircle(0f, 0f, 200f, Path.Direction.CW)
        path2.addRect(0f, -200f, 200f, 200f, Path.Direction.CW)
        path3.addCircle(0f, -100f, 100f, Path.Direction.CW)
        path4.addCircle(0f, 100f, 100f, Path.Direction.CW)
        path1.op(path2, Path.Op.DIFFERENCE)
        path1.op(path3, Path.Op.UNION)
        path1.op(path4, Path.Op.DIFFERENCE)

        canvas?.drawPath(path1, mPaint)

        mPaint.reset()
        mPaint.color = Color.WHITE
        canvas?.drawCircle(0f, 100f, 10f, mPaint)
        canvas?.drawCircle(0f, -100f, 10f, mPaint)

    }
}