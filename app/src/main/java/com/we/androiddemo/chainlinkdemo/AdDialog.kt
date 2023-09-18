package com.we.androiddemo.chainlinkdemo

import android.content.Context
import android.util.Log

/**
 * @Author : dongfang
 * @Created Time : 2023-09-18  10:26
 * @Description: 广告弹窗
 */
class AdDialog(context: Context) : AbsDialog(context) {
    private val adResponse = AdResponse(1, "广告。。。")
    override fun getPriority(): Int = 1

    override fun needShowDialog(): Boolean {
        //区分不同的广告
        return needShow(AD_DIALOG_KEY + adResponse.id)
    }

    override fun show() {
        Log.d(javaClass.simpleName, "广告弹窗")
        setShown(AD_DIALOG_KEY + adResponse.id, true)
    }
}