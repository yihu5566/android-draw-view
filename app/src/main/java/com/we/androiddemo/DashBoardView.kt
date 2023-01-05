package com.we.androiddemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathDashPathEffect
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import com.we.androiddemo.Utils.dp2px
import kotlin.math.cos
import kotlin.math.sin

/**
 * @Author : dongfang
 * @Created Time : 2023-01-05  09:07
 * @Description:
 */

class DashBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val border = 150f.dp2px()
    val openAngle = 120f
    val dashH = 20f
    val dashW = 10f
    val scaleCount = 20
    lateinit var pathDash: PathDashPathEffect
    val lineLong = 120f.dp2px()

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val dash = Path()
    val path = Path()

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        dash.addRect(0f, 0f, dashW, dashH, Path.Direction.CCW)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(
            width / 2 - border,
            height / 2 - border,
            width / 2 + border,
            height / 2 + border,
            90f + openAngle / 2, 360f - openAngle
        )
        val pathMeasure = PathMeasure(path, false)
        pathDash = PathDashPathEffect(
            dash,
            (pathMeasure.length - dashW) / scaleCount,
            0f,
            PathDashPathEffect.Style.ROTATE
        )
    }


    override fun onDraw(canvas: Canvas?) {
        //绘制弧形
        canvas?.drawPath(path, paint)
        //绘制刻度
        paint.pathEffect = pathDash
        canvas?.drawPath(path, paint)
        paint.pathEffect = null
        //画指针
        canvas?.drawLine(
            width / 2f,
            height / 2f,
            width / 2f + (lineLong * cos(Math.toRadians(scale2Degrees(10)))).toFloat(),
            height / 2f + (lineLong * sin(Math.toRadians(scale2Degrees(10)))).toFloat(), paint
        )
    }

    fun scale2Degrees(scale: Int): Double {
        val indexAngle = (360f - openAngle) / 20
        return (90 + openAngle / 2) + scale * indexAngle.toDouble()
    }
}