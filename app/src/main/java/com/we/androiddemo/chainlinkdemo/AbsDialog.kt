package com.we.androiddemo.chainlinkdemo

import android.content.Context
import android.content.SharedPreferences

/**
 * @Author : dongfang
 * @Created Time : 2023-09-18  10:14
 * @Description:  抽象弹窗
 */
abstract class AbsDialog(private val context: Context) {
    //下一个要弹出的弹窗
    private var nextDialog: AbsDialog? = null

    //弹窗优先级 隐私》广告》评价
    abstract fun getPriority(): Int

    abstract fun needShowDialog(): Boolean


    fun setNextDialog(dialog: AbsDialog) {
        nextDialog = dialog
    }

    fun showDialog() {
        if (needShowDialog()) {
            show()
        } else {
            nextDialog?.showDialog()
        }

    }

    abstract fun show()

    fun needShow(key: String): Boolean {
        val sp: SharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        return sp.getBoolean(key, true)
    }


    open fun setShown(key: String, show: Boolean) {
        val sp: SharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        sp.edit().putBoolean(key, !show).apply()
    }

    companion object {
        const val LOG_TAG = "Dialog"
        const val SP_NAME = "dialog"
        const val POLICY_DIALOG_KEY = "policy_dialog"
        const val AD_DIALOG_KEY = "ad_dialog"
        const val PRAISE_DIALOG_KEY = "praise_dialog"
    }
}