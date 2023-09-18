package com.we.androiddemo.chainlinkdemo

import android.content.Context
import android.util.Log

/**
 * @Author : dongfang
 * @Created Time : 2023-09-18  10:26
 * @Description: 评价弹窗
 */
class CommentDialog(context: Context) : AbsDialog(context) {
    override fun getPriority(): Int = 0

    override fun needShowDialog(): Boolean {
        return needShow(PRAISE_DIALOG_KEY)
    }

    override fun show() {
        Log.d(javaClass.simpleName, "评论弹窗")
        setShown(PRAISE_DIALOG_KEY, true)
    }
}