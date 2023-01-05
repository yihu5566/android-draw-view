package com.we.opengl3d

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 * @Author : dongfang
 * @Created Time : 2022-09-22  14:36
 * @Description:
 */
class PathSearchDemo constructor(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    var mWith: Int = 0
    var mHeight: Int = 0
    val mPaint: Paint
    var currentValue = 1f

    lateinit var path_search: Path
    lateinit var path_circle: Path
    lateinit var pathMeasure: PathMeasure
    var pos = FloatArray(2)

    var isSearch = false

    init {
        mPaint = Paint()
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 10f
        initPath()

    }

    private fun initPath() {
        path_search = Path()
        path_circle = Path()
        pathMeasure = PathMeasure()

        // 注意,不要到360度,否则内部会自动优化,测量不能取到需要的数值
        val oval1 = RectF(-50f, -50f, 50f, 50f) // 放大镜圆环
        path_search.addArc(oval1, 45f, 359.9f)

        val oval2 = RectF(-100f, -100f, 100f, 100f) // 外部圆环
        path_circle.addArc(oval2, 45f, -359.9f)

        pathMeasure.setPath(path_circle, false)
        pathMeasure.getPosTan(0f, pos, null)
        path_search.lineTo(pos[0], pos[1])
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWith = w
        mHeight = h
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.translate((mWith / 2).toFloat(), (mHeight / 2).toFloat())
//        canvas?.drawLine(-(width / 2).toFloat(), 0f, (width / 2).toFloat(), 0f, mPaint)
//        canvas?.drawLine(0f, -(mHeight / 2).toFloat(), 0f, (mHeight / 2).toFloat(), mPaint)

        if (isSearch) {
            currentValue -= 0.005f
            if (currentValue <= 0) {
                currentValue = 0f
                isSearch = false
            }
        } else {
            currentValue += 0.005f
            if (currentValue >= 1) {
                currentValue = 1f
                isSearch = true
            }
        }

        pathMeasure.setPath(path_search, false)
        val dst = Path()
        pathMeasure.getSegment(pathMeasure.length * currentValue, pathMeasure.length, dst, true)
        canvas?.drawPath(dst, mPaint)
        invalidate()
    }

}