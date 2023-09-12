package com.we.androiddemo.news_comment.data

/**
 * @Author : dongfang
 * @Created Time : 2023-09-12  15:50
 * @Description:
 */
interface ICommentEntity {
    val id: Int
    val content: CharSequence
    val userId: Int
    val userName: CharSequence
}

data class CommentLevel1(
    override val id: Int,
    override val content: CharSequence,
    override val userId: Int,
    override val userName: CharSequence,
    val level2Count: Int,
) : ICommentEntity

data class CommentLevel2(
    override val id: Int,
    override val content: CharSequence,
    override val userId: Int,
    override val userName: CharSequence,
    val parentId: Int,
    val hot: Boolean = false,
) : ICommentEntity