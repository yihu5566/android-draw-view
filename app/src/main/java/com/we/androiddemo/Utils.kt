package com.we.androiddemo

import android.content.res.Resources
import android.util.TypedValue

/**
 * @Author : dongfang
 * @Created Time : 2023-01-05  09:30
 * @Description:
 */
object Utils {
    fun Float.dp2px(): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
        )
    }
}
