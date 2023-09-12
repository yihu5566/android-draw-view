package com.we.androiddemo.news_comment.logic

import com.we.androiddemo.news_comment.CommentItem

/**
 * @Author : dongfang
 * @Created Time : 2023-09-12  15:12
 * @Description:
 */
interface Reducer {
    val reduce: suspend List<CommentItem>.() -> List<CommentItem>
}
