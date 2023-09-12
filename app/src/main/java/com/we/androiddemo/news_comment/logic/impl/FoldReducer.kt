package com.we.androiddemo.news_comment.logic.impl

import com.we.androiddemo.news_comment.CommentItem
import com.we.androiddemo.news_comment.logic.Reducer

/**
 * @Author : dongfang
 * @Created Time : 2023-09-12  16:20
 * @Description:
 */
data class FoldReducer(val folding: CommentItem.Folding) : Reducer {
    override val reduce: suspend List<CommentItem>.() -> List<CommentItem> = {
        val parentIndex = indexOfFirst { it.id == folding.parentId }
        val foldingIndex = indexOf(folding)
        this - subList(parentIndex + 3, foldingIndex).toSet().map {
            if (it is CommentItem.Folding && it == folding) {
                it.copy(page = 1, state = CommentItem.Folding.State.IDLE)
            } else {
                it
            }
        }.toSet()
    }
}
