package com.we.androiddemo.xfermode

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.we.androiddemo.px

/**
 * @Author : dongfang
 * @Created Time : 2023-01-05  09:07
 * @Description:xfermode
 */

class XfermodeView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bounds = RectF(150f.px, 50f.px, 300f.px, 200f.px)
    val bitmapW = 150f.px
    val bitmapH = 150f.px
    val myXfermodel = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)

    //绘制矩形区域
    val ref = RectF(0f.px, 50f.px, 100f.px, 150f.px)
    val ref_c = RectF(50f.px, 0f.px, 150f.px, 100f.px)

    val srcBitmap = Bitmap.createBitmap(bitmapW.toInt(), bitmapH.toInt(), Bitmap.Config.ARGB_8888)
    val dstBitmap = Bitmap.createBitmap(bitmapW.toInt(), bitmapH.toInt(), Bitmap.Config.ARGB_8888)

    init {
        //绘制蓝色的矩形
        val canvas = Canvas(srcBitmap)
        paint.color = Color.BLUE
        canvas.drawRect(ref, paint)

        //绘制红色圆形
        paint.color = Color.RED
        canvas.setBitmap(dstBitmap)
        canvas.drawOval(ref_c, paint)
    }

    override fun onDraw(canvas: Canvas) {
        val count = canvas.saveLayer(bounds, null)
        canvas.drawBitmap(dstBitmap, 150f.px, 50f.px, paint)
        paint.xfermode = myXfermodel
        canvas.drawBitmap(srcBitmap, 150f.px, 50f.px, paint)
        paint.xfermode = null
        canvas.restoreToCount(count)
    }
}