package com.we.androiddemo

import android.content.Context
import android.os.Looper
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.concurrent.thread

/**
 * @Author : dongfang
 * @Created Time : 2023-01-09  16:27
 * @Description:
 */
class TabLayoutView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

requestLayout()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }
}