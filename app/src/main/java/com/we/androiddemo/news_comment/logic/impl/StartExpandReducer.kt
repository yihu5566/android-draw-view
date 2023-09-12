package com.we.androiddemo.news_comment.logic.impl

import com.we.androiddemo.news_comment.CommentItem
import com.we.androiddemo.news_comment.logic.Reducer

/**
 * @Author : dongfang
 * @Created Time : 2023-09-12  16:37
 * @Description:
 */
class StartExpandReducer(
    val folding: CommentItem.Folding,
) : Reducer {
    override val reduce: suspend List<CommentItem>.() -> List<CommentItem>
        get() = {
            map {
                if (it is CommentItem.Folding && it == folding) {
                    it.copy(state = CommentItem.Folding.State.LOADING)
                } else {
                    it
                }
            }
        }
}