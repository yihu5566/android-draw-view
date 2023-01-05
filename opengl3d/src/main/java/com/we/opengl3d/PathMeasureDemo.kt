package com.we.opengl3d

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.atan2


/**
 * @Author : dongfang
 * @Created Time : 2022-09-22  14:36
 * @Description:
 */
class PathMeasureDemo constructor(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    var mWith: Int = 0
    var mHeight: Int = 0
    val mPaint: Paint

    var currentValue = 0f
    var pos = FloatArray(2)
    var tan = FloatArray(2)
    var mBitmap: Bitmap
    var mMatrix: Matrix

    init {
        mPaint = Paint()
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10f
        val options = BitmapFactory.Options()
        options.inSampleSize = 6 // 缩放图片

        mBitmap = BitmapFactory.decodeResource(context!!.resources, R.drawable.arrow, options)
        mMatrix = Matrix()

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

        var mPath = Path()
        mPath.addCircle(0f, 0f, 200f, Path.Direction.CW)
        val pathMeasure = PathMeasure(mPath, false)
        currentValue += 0.005f
        if (currentValue >= 1) {
            currentValue = 0.0f
        }
        pathMeasure.getMatrix(
            pathMeasure.length * currentValue, mMatrix,
            PathMeasure.TANGENT_MATRIX_FLAG or PathMeasure.POSITION_MATRIX_FLAG
        )

//        pathMeasure.getPosTan(pathMeasure.length * currentValue, pos, tan)
//        mMatrix.reset()
//        val degrees = atan2(tan[1].toDouble(), tan[0] * 180.0 / Math.PI)
//        mMatrix.postRotate(
//            degrees.toFloat(), (mBitmap.width / 2).toFloat(),
//            (mBitmap.height / 2).toFloat()
//        )
//        mMatrix.postTranslate(pos[0] - mBitmap.width / 2, pos[1] - mBitmap.height / 2)
        mMatrix.preTranslate((-mBitmap.width / 2).toFloat(), (-mBitmap.height / 2).toFloat())
        canvas?.drawPath(mPath, mPaint)
        canvas?.drawBitmap(mBitmap, mMatrix, mPaint)
        invalidate()
    }

}