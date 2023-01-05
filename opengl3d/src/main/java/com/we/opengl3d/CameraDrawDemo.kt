package com.we.opengl3d

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @Author : dongfang
 * @Created Time : 2022-09-22  10:56
 * @Description:
 */
class CameraDrawDemo : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    var mWith: Int = 0
    var mHeight: Int = 0
    val mPaint: Paint = Paint()
    var mBitmap: Bitmap
    var mMatrix: Matrix

    var currentValue = 0f
    var scale: Float

    var childIndex: Int = 1


    init {
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.FILL
        mPaint.strokeWidth = 10f
        mMatrix = Matrix()

        val options = BitmapFactory.Options()
        options.inSampleSize = 4 // 缩放图片
        mBitmap = BitmapFactory.decodeResource(context!!.resources, R.drawable.time, options)
        // 获取手机像素密度 （即dp与px的比例）
        scale = context.resources.displayMetrics.density
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
        currentValue += 0.005f
        if (currentValue >= 1) {
            currentValue = 0.0f
        }
        val camera = Camera()
        //z轴深度调节
//        camera.translate(0.0f, 0.0f, 1000f)

        camera.rotateX(currentValue * 360)
        camera.getMatrix(mMatrix)
        val floats = FloatArray(9)
        mMatrix.getValues(floats)
        floats[6] = floats[6] / scale
        floats[7] = floats[7] / scale
        mMatrix.setValues(floats)
        mMatrix.preTranslate(-(mBitmap.width / 2).toFloat(), -(mBitmap.height / 2).toFloat())
        mMatrix.postTranslate((mBitmap.width / 2).toFloat(), (mBitmap.height / 2).toFloat())
        canvas?.drawBitmap(mBitmap, mMatrix, mPaint)

        canvas?.save()
//        camera.save()
//
//        camera.restore()
        canvas?.restore()
//        repeat(2) {
//            childIndex++
        drawChild(canvas)
//        }
        canvas?.save()
//        camera.save()
        invalidate()

    }

    private fun drawChild(canvas: Canvas?) {
       val camera = Camera()
        val matrix = Matrix()
        camera.rotateY(currentValue * 360)
        camera.getMatrix(matrix)
        matrix.postTranslate(-(mBitmap.width).toFloat(), -(mBitmap.height).toFloat())
        val floats = FloatArray(9)
        matrix.getValues(floats)
        floats[6] = floats[6] / scale
        floats[7] = floats[7] / scale
        matrix.setValues(floats)
        matrix.preTranslate(-(mBitmap.width / 2).toFloat(), -(mBitmap.height / 2).toFloat())
        matrix.postTranslate((mBitmap.width / 2).toFloat(), (mBitmap.height / 2).toFloat())
        camera.save()
        canvas?.drawBitmap(mBitmap, matrix, mPaint)
        canvas?.concat(matrix)

    }
}