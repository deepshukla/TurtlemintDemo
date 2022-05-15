package com.example.turtlemintdemo.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.turtlemintdemo.R
import com.example.turtlemintdemo.model.Issue
import com.example.turtlemintdemo.utils.ItemClickListener
import com.example.turtlemintdemo.utils.Utills


class MainAdapter(
    private val issues: ArrayList<Issue>, private val onRecyclerViewItemClickListener: ItemClickListener?
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(issue: Issue, onRecyclerViewItemClickListener: ItemClickListener?, position: Int) {
            val continuation = if (issue.description?.length?:0 > 200) "..." else ""
            itemView.findViewById<TextView>(R.id.titleTextView).text = issue.title
            itemView.findViewById<TextView>(R.id.descriptionTextView).text = "${issue.description?.take(200)} $continuation"
            itemView.findViewById<TextView>(R.id.dateTextView).text = "updated on ${issue.updatedAt?.let { Utills.convertDateFormet(it) }}"
            itemView.findViewById<TextView>(R.id.userNameTextView).text = issue.user.username
           val imageView=itemView.findViewById<AppCompatImageView>(R.id.imageView)
            Glide.with(imageView)
                .load(issue.user.avatar)
                .into(imageView)
            itemView.setOnClickListener{
                onRecyclerViewItemClickListener?.onItemClick(it,position)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.issue_list_item, parent,
                false
            )
        )

    override fun getItemCount(): Int = issues.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(issues[position],onRecyclerViewItemClickListener,position)

    fun addData(list: List<Issue>) {
        issues.addAll(list)
    }

}