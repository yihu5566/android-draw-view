package com.we.opengl3d

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @Author : dongfang
 * @Created Time : 2022-09-30  15:59
 * @Description:
 */
class RegionDemo constructor(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var up_p: Path
    var down_p: Path
    var left_p: Path
    var right_p: Path
    var center_p: Path
    var up: Region
    var down: Region
    var left: Region
    var right: Region
    var center: Region

    val mMatrix = Matrix()

    val mPaint = Paint()
    val mSelectPaint = Paint()

    var CENTER = 0
    var UP = 1
    var RIGHT = 2
    var DOWN = 3
    var LEFT = 4
    var touchFlag = -1
    var currentFlag = -1

    init {
        mPaint.color = Color.BLACK
        mPaint.isAntiAlias = true

        mSelectPaint.color = Color.RED
        mSelectPaint.isAntiAlias = true

        center_p = Path()
        left_p = Path()
        right_p = Path()
        up_p = Path()
        down_p = Path()

        center = Region()
        left = Region()
        right = Region()
        up = Region()
        down = Region()


    }

    var mWidth = 0
    var minWidth = 0.0
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mMatrix.reset()

        val region = Region(-w, -h, w, h)
        mWidth = if (w > h) {
            h
        } else {
            w
        }

        minWidth = mWidth * 0.8

        //绘制的矩形区域
        val br = (minWidth / 2).toFloat()
        val bigCircle = RectF(-br, -br, br, br)
        //小圆
        val sr = (minWidth / 4).toFloat()
        val smallCircle = RectF(-sr, -sr, sr, sr)

        //扫过的角度
        val bigSweepAngle = 84f
        val smallSweepAngle = -80f

        //中心
        center_p.addCircle(0f, 0f, (0.2 * minWidth).toFloat(), Path.Direction.CW)
        center.setPath(center_p, region)

        right_p.addArc(bigCircle, -40f, bigSweepAngle)
        right_p.arcTo(smallCircle, 40f, smallSweepAngle)
        right_p.close()
        right.setPath(right_p, region)

        down_p.addArc(bigCircle, 50f, bigSweepAngle)
        down_p.arcTo(smallCircle, 130f, smallSweepAngle)
        down_p.close()
        down.setPath(down_p, region)

        left_p.addArc(bigCircle, 140f, bigSweepAngle)
        left_p.arcTo(smallCircle, 220f, smallSweepAngle)
        left_p.close()
        left.setPath(left_p, region)

        up_p.addArc(bigCircle, 230f, bigSweepAngle)
        up_p.arcTo(smallCircle, 310f, smallSweepAngle)
        up_p.close()
        up.setPath(up_p, region)


    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val array = FloatArray(2)
        array[0] = event.rawX
        array[1] = event.rawY
        mMatrix.mapPoints(array)

        val x = array[0].toInt()
        val y = array[1].toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchFlag = getTouchedPath(x, y)
                currentFlag = touchFlag

            }
            MotionEvent.ACTION_MOVE -> {
                touchFlag = getTouchedPath(x, y)

            }
            MotionEvent.ACTION_UP -> {
                touchFlag = getTouchedPath(x, y)
                //处理点击时间回调

                touchFlag = -1
                currentFlag = -1
            }
            else -> {}
        }
        invalidate()
        return true
    }


    fun getTouchedPath(x: Int, y: Int): Int {
        if (center.contains(x, y)) {
            return 0;
        } else if (up.contains(x, y)) {
            return 1;
        } else if (right.contains(x, y)) {
            return 2;
        } else if (down.contains(x, y)) {
            return 3;
        } else if (left.contains(x, y)) {
            return 4;
        }
        return -1;
    }

    override fun onDraw(canvas: Canvas) {
        canvas.translate((mWidth / 2).toFloat(), (mWidth / 2).toFloat())

        if (mMatrix.isIdentity) {
            canvas.matrix.invert(mMatrix)
        }
        canvas.drawPath(center_p, mPaint)
        canvas.drawPath(right_p, mPaint)
        canvas.drawPath(down_p, mPaint)
        canvas.drawPath(left_p, mPaint)
        canvas.drawPath(up_p, mPaint)

        if (currentFlag == CENTER) {
            canvas.drawPath(center_p, mSelectPaint);
        } else if (currentFlag == UP) {
            canvas.drawPath(up_p, mSelectPaint);
        } else if (currentFlag == RIGHT) {
            canvas.drawPath(right_p, mSelectPaint);
        } else if (currentFlag == DOWN) {
            canvas.drawPath(down_p, mSelectPaint);
        } else if (currentFlag == LEFT) {
            canvas.drawPath(left_p, mSelectPaint);
        }
    }
}