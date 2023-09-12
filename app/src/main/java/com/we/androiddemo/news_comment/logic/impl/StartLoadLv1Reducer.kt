package com.we.androiddemo.news_comment.logic.impl

import com.we.androiddemo.news_comment.CommentItem
import com.we.androiddemo.news_comment.logic.Reducer

/**
 * @Author : dongfang
 * @Created Time : 2023-09-12  16:40
 * @Description:
 */

class StartLoadLv1Reducer : Reducer {
    override val reduce: suspend List<CommentItem>.() -> List<CommentItem> = {
        map {
            if (it is CommentItem.Loading) {
                it.copy(
                    state = CommentItem.Loading.State.LOADING
                )
            } else {
                it
            }
        }
    }
}