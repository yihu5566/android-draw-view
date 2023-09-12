package com.we.androiddemo.news_comment

import com.we.androiddemo.news_comment.data.FakeApi
import com.we.androiddemo.news_comment.logic.Reducer
import com.we.androiddemo.news_comment.logic.impl.Entity2ItemMapper

/**
 * @Author : dongfang
 * @Created Time : 2023-09-12  15:43
 * @Description:
 */
data class ExpandReducer(
    val folding: CommentItem.Folding,
) : Reducer {
    private val mapper by lazy { Entity2ItemMapper() }
    override val reduce: suspend List<CommentItem>.() -> List<CommentItem>
        get() = {
            val foldingIndex = indexOf(folding)
            val loaded =
                FakeApi.getLevel2Comments(folding.parentId, folding.page, folding.pageSize)
                    .getOrNull()
                    ?.map(mapper::invoke) ?: emptyList()
            toMutableList().apply {
                addAll(foldingIndex, loaded)
            }.map {
                if (it is CommentItem.Folding && it == folding) {
                    val state =
                        if (it.page > 5) CommentItem.Folding.State.LOADED_ALL else CommentItem.Folding.State.IDLE
                    it.copy(page = it.page + 1, state = state)
                } else {
                    it
                }
            }
        }


}
