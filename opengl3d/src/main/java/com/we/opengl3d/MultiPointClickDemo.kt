package com.we.opengl3d

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


/**
 * @Author : dongfang
 * @Created Time : 2022-09-26  11:07
 * @Description: 弹跳小球
 */
class MultiPointClickDemo constructor(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    val TAG = "Gcs"

    val mPaint = Paint()
    private var mWidth: Int = 0
    private var mHeight: Int = 0

    //小球起始位置
    private var mStartX: Float = 0f
    private var mStartY: Float = 0f
    private var mEdgeLength: Int = 200

    //活动边界
    private val mRect = RectF(
        mStartX,
        mStartY, (mStartX + mEdgeLength), (mStartY + mEdgeLength)
    )

    private var mCanFail = false // 是否可以拖动

    private var mSpeedX = 0f // 像素/s
    private var mSpeedY = 0f

    //是否可以修正
    private var mXFixed = false
    private var mYFixed = false
    var mBitmap: Bitmap
    val mGestureDetector: GestureDetector
    val simpleOnGestureListener = object : GestureDetector.SimpleOnGestureListener() {

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (!mCanFail) return false

            //更新变化位置
            mSpeedX = velocityX
            mSpeedY = velocityY
            mHandler.removeCallbacks(mRunnable)
            mHandler.post(mRunnable)
            return super.onFling(e1, e2, velocityX, velocityY)
        }

    }

    init {
        mPaint.color = Color.WHITE
        mPaint.style = Paint.Style.FILL
        mGestureDetector = GestureDetector(context, simpleOnGestureListener)
        mBitmap = BitmapFactory.decodeResource(context?.resources, R.drawable.ball)
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        mStartX = ((mWidth - mEdgeLength) / 2).toFloat()
        mStartY = ((mHeight - mEdgeLength) / 2).toFloat()
        refreshRectByCurrentPoint()
    }

    private val mHandler: Handler = Handler(Looper.myLooper()!!)
    private val mRunnable = object : Runnable {
        override fun run() {
            mStartX = (mStartX + mSpeedX / 30)
            mStartY = (mStartY + mSpeedY / 30)
            //速度变化，愈来越小
            mSpeedX *= 0.99f
            mSpeedY *= 0.99f

            if (abs(mSpeedX) < 10) {
                mSpeedX = 0f
            }
            if (abs(mSpeedY) < 10) {
                mSpeedY = 0f
            }

            if (refreshRectByCurrentPoint()) {
                if (mXFixed) {
                    mSpeedX = -mSpeedX
                }
                if (mYFixed) {
                    mSpeedY = -mSpeedY
                }
            }

            invalidate()
            if (mSpeedX == 0f && mSpeedY == 0f) {
                mHandler.removeCallbacks(this)
                return
            }

            mHandler.postDelayed(this, 33);
        }
    }


    //刷新球的位置
    private fun refreshRectByCurrentPoint(): Boolean {
        var fixed = false
        mXFixed = false
        mYFixed = false

        // 修正坐标
        if (mStartX < 0) {
            mStartX = 0f
            fixed = true;
            mXFixed = true;
        }
        if (mStartY < 0) {
            mStartY = 0f
            fixed = true;
            mYFixed = true;
        }
        if (mStartX + mEdgeLength > mWidth) {
            mStartX = (mWidth - mEdgeLength).toFloat();
            fixed = true;
            mXFixed = true;
        }
        if (mStartY + mEdgeLength > mHeight) {
            mStartY = (mHeight - mEdgeLength).toFloat();
            fixed = true;
            mYFixed = true;
        }

        mRect.left = mStartX
        mRect.top = mStartY
        mRect.right = (mStartX + mEdgeLength)
        mRect.bottom = (mStartY + mEdgeLength)
        return fixed;

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mGestureDetector.onTouchEvent(event)
        val index = event!!.actionIndex

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "第1个手指按下")
                if (contains(event.x, event.y)) {
                    mCanFail = true
                    mStartX = event.x
                    mStartY = event.y
                    mSpeedX = 0f
                    mSpeedY = 0f
                } else {
                    mCanFail = false
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (mCanFail) {
                    mStartX = (event.x)
                    mStartY = (event.y)
                    refreshRectByCurrentPoint()
                    invalidate()
                }
            }

        }
        return true
    }

    fun contains(x: Float, y: Float): Boolean {
        //小球半径
        val radius = mEdgeLength / 2

        //贴左上角
        val centerX = mRect.left + radius
        val centerY = mRect.top + radius

        return sqrt((x - centerX).pow(2) + (y - centerY).pow(2)) <= radius;
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawOval(mRect, mPaint)
        canvas.drawBitmap(
            mBitmap, Rect(0, 0, mBitmap.width, mBitmap.height),
            mRect, mPaint
        )
    }

}