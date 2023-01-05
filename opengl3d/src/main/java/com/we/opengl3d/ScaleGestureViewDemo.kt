package com.we.opengl3d

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View


/**
 * @Author : dongfang
 * @Created Time : 2022-09-26  11:07
 * @Description: 缩放手势
 */
class ScaleGestureViewDemo constructor(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    val TAG = "Gcs"

    val mPaint = Paint()
    private var mWidth: Int = 0
    private var mHeight: Int = 0

    var focusX: Float = 0f
    var focusY: Float = 0f
    var scaleFactor: Float = 0f
    var mMatrix: Matrix = Matrix()
    var isCanDraw: Boolean = true


    var mBitmap: Bitmap = BitmapFactory.decodeResource(context?.resources, R.drawable.time)
    val mGestureDetector: ScaleGestureDetector
    val simpleOnGestureListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            return true
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            Log.i(TAG, "focusX = " + detector.focusX)      // 缩放中心，x坐标
            Log.i(TAG, "focusY = " + detector.focusY)      // 缩放中心y坐标
            Log.i(TAG, "scale = " + detector.scaleFactor)   // 缩放因子
            focusX = detector.focusX
            focusY = detector.focusY
            scaleFactor = detector.scaleFactor
            mMatrix.postScale(scaleFactor, scaleFactor)
            isCanDraw = true

            invalidate()
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
            val floats = FloatArray(9)
            mMatrix.getValues(floats)
            floats[6] = floats[6]
            floats[7] = floats[7]
            mMatrix.setValues(floats)
            mMatrix.preTranslate(-(focusX / 2), -(focusY / 2))
            mMatrix.postTranslate((focusX / 2), (focusY / 2))
            isCanDraw = false
            invalidate()
        }
    }

    init {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.FILL
        mGestureDetector = ScaleGestureDetector(context, simpleOnGestureListener)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    var tranX = 0f
    var tranY = 0f
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mGestureDetector.onTouchEvent(event)

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                tranX = event.rawX
                tranY = event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                tranX = event.rawX - tranX
                tranY = event.rawY - tranY
                Log.d(TAG, ":::$tranX---$tranY")

                if (event.pointerCount == 1) {
                    isCanDraw = false
                    postInvalidate()
                }
            }

            else -> {

            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
//        mMatrix.reset()
//        mMatrix.postTranslate((mBitmap.width - tranX)/ 2, (mBitmap.height - tranY) / 2)
//        canvas.drawBitmap(mBitmap, mMatrix, mPaint)
        canvas.translate(tranX,tranY)
        canvas.drawCircle(focusX,focusY,100f,mPaint)
    }
}