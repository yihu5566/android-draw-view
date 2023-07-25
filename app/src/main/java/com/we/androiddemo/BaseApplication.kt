package com.we.androiddemo

import android.app.Application
import com.airbnb.mvrx.Mavericks

/**
 * @Author : dongfang
 * @Created Time : 2023-07-25  15:45
 * @Description:
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}