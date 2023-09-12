package com.we.androiddemo.news_comment.logic.impl

import com.we.androiddemo.news_comment.CommentItem
import com.we.androiddemo.news_comment.data.CommentLevel1
import com.we.androiddemo.news_comment.data.CommentLevel2
import com.we.androiddemo.news_comment.data.ICommentEntity
import com.we.androiddemo.news_comment.logic.Mapper

/**
 * @Author : dongfang
 * @Created Time : 2023-09-12  15:53
 * @Description:
 */
class Entity2ItemMapper : Mapper<ICommentEntity, CommentItem> {
    override fun invoke(entity: ICommentEntity): CommentItem {
        return when (entity) {
            is CommentLevel1 -> {
                CommentItem.Level1(
                    id = entity.id,
                    content = entity.content,
                    userId = entity.userId,
                    userName = entity.userName,
                    level2Count = entity.level2Count,
                )
            }

            is CommentLevel2 -> {
                CommentItem.Level2(
                    id = entity.id,
                    content = entity.content,
                    userId = entity.userId,
                    userName = entity.userName,
                    parentId = entity.parentId,
                )
            }

            else -> {
                throw IllegalArgumentException("not implemented entity: $entity")
            }
        }

    }
}