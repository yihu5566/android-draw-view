package com.we.androiddemo.news_comment.logic.impl

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.we.androiddemo.R
import com.we.androiddemo.news_comment.CommentItem
import com.we.androiddemo.news_comment.data.FakeApi
import com.we.androiddemo.news_comment.logic.Reducer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @Author : dongfang
 * @Created Time : 2023-09-12  16:57
 * @Description:
 */
class ReplyReducer(private val commentItem: CommentItem, private val context: Context) : Reducer {
    private val mapper: Entity2ItemMapper by lazy { Entity2ItemMapper() }

    override val reduce: suspend List<CommentItem>.() -> List<CommentItem>
        get() = {
            val content = withContext(Dispatchers.Main) {
                suspendCoroutine { it1 ->
                    ReplyDialog(context) {
                        it1.resume(it)
                    }.show()
                }
            }
            val parentId = (commentItem as? CommentItem.Level1)?.id
                ?: (commentItem as? CommentItem.Level2)?.parentId ?: 0
            val replyItem = mapper.invoke(FakeApi.addComment(parentId, content))
            val insertIndex = indexOf(commentItem) + 1
            toMutableList().apply {
                add(insertIndex, replyItem)
            }

        }
}

class ReplyDialog(context: Context, private val callback: (String) -> Unit) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_reply)
        val editText = findViewById<EditText>(R.id.content)
        findViewById<Button>(R.id.submit).setOnClickListener {
            if (editText.text.toString().isBlank()) {
                Toast.makeText(context, "评论不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            callback.invoke(editText.text.toString())
            dismiss()
        }
    }
}