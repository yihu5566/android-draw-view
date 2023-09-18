package com.we.androiddemo.chainlinkdemo

import android.content.Context
import android.util.Log

/**
 * @Author : dongfang
 * @Created Time : 2023-09-18  10:26
 * @Description: 隐私弹窗
 */
class PolicyDialog(context: Context) : AbsDialog(context) {
    override fun getPriority(): Int = 0

    override fun needShowDialog(): Boolean {
        return needShow(POLICY_DIALOG_KEY)
    }

    override fun show() {
        Log.d(javaClass.simpleName, "隐私弹窗")
        setShown(POLICY_DIALOG_KEY, true)
    }
}