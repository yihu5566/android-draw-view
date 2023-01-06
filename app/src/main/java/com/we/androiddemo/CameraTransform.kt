package com.we.androiddemo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @Author : dongfang
 * @Created Time : 2023-01-06  11:18
 * @Description: 通过camera 模拟三维效果
 */
class CameraTransform(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bitmapWidth = 150f.px
    val marginSpace = 50f.px
    private val mCamera = Camera()
    private var bitmapAvatar: Bitmap

    var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var rotationFlip = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        bitmapAvatar = getAvatar(bitmapWidth.toInt())
        mCamera.setLocation(0f, 0f, -6f * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        //裁剪上半部分
        canvas.save()
        canvas.translate(marginSpace + bitmapWidth / 2, marginSpace + bitmapWidth / 2)
        canvas.rotate(-rotationFlip)
        mCamera.save()
        mCamera.rotateX(topFlip)
        mCamera.applyToCanvas(canvas)
        mCamera.restore()
        canvas.clipRect(-(bitmapWidth), -(bitmapWidth), bitmapWidth, 0f)
        canvas.rotate(rotationFlip)
        canvas.translate(-(marginSpace + bitmapWidth / 2), -(marginSpace + bitmapWidth / 2))
        canvas.drawBitmap(bitmapAvatar, marginSpace, marginSpace, paint)
        canvas.restore()

        //裁剪下半部分
        canvas.save()
        canvas.translate(marginSpace + bitmapWidth / 2, marginSpace + bitmapWidth / 2)
        canvas.rotate(-rotationFlip)
        mCamera.save()
        mCamera.rotateX(bottomFlip)
        mCamera.applyToCanvas(canvas)
        mCamera.restore()
        canvas.clipRect(-(bitmapWidth), 0f, bitmapWidth, bitmapWidth)
        canvas.rotate(rotationFlip)
        canvas.translate(-(marginSpace + bitmapWidth / 2), -(marginSpace + bitmapWidth / 2))
        canvas.drawBitmap(bitmapAvatar, marginSpace, marginSpace, paint)
        canvas.restore()
    }


    /**
     * 获取头像
     */
    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.time_small)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.time_small, options)
    }
}