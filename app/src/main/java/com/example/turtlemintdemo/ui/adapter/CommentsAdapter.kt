package com.example.turtlemintdemo.ui.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.turtlemintdemo.R
import com.example.turtlemintdemo.model.Comment
import java.util.ArrayList


class CommentsAdapter(issues: List<Comment>?) : RecyclerView.Adapter<CommentsAdapter.CommentsVieHolder>() {

    private var commentsList = ArrayList<Comment>()

    init {
        this.commentsList = issues as ArrayList<Comment>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsVieHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(
            R.layout.comments_list_item,
            parent, false)
        return CommentsVieHolder(itemView)  }


    override fun onBindViewHolder(holder: CommentsVieHolder, position: Int) {
        val commentItem = commentsList[position]

        holder?.issuesListItem(commentItem)
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }



    fun addComments(issues: List<Comment>){
        val initPosition = commentsList.size
        commentsList.addAll(issues)
        notifyItemRangeInserted(initPosition, commentsList.size)
    }

    inner class CommentsVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var commentUser = itemView.findViewById<TextView>(R.id.comment_user)
        var commentBody = itemView.findViewById<TextView>(R.id.comment_body)


        fun issuesListItem(commentItem: Comment) {
            commentUser.text = commentItem.user.login
            commentBody.text = commentItem.body

        }
    }


}
