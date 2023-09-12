package com.we.androiddemo.news_comment.logic.impl

import com.we.androiddemo.news_comment.CommentItem
import com.we.androiddemo.news_comment.data.FakeApi
import com.we.androiddemo.news_comment.logic.Reducer

/**
 * @Author : dongfang
 * @Created Time : 2023-09-12  16:34
 * @Description:
 */
class LoadLv1Reducer : Reducer {
    private val mapper by lazy { Entity2ItemMapper() }
    override val reduce: suspend List<CommentItem>.() -> List<CommentItem>
        get() = {
            val loading = get(size - 1) as CommentItem.Loading
            val loaded =
                FakeApi.getComments(loading.page + 1, 5).getOrNull()?.map(mapper::invoke)
                    ?: emptyList()
            val grouped = loaded.groupBy {
                (it as? CommentItem.Level1)?.id ?: (it as? CommentItem.Level2)?.parentId
                ?: throw IllegalArgumentException("invalid comment item")
            }.flatMap {
                it.value + CommentItem.Folding(parentId = it.key)
            }
            toMutableList().apply {
                removeAt(size - 1)
                this += grouped
                this += loading.copy(
                    state = CommentItem.Loading.State.IDLE,
                    page = loading.page + 1
                )
            }.toList()
        }
}
