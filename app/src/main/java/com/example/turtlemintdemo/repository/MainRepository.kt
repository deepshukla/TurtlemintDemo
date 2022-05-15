package com.example.turtlemintdemo.repository

import com.example.turtlemintdemo.model.Comment
import com.example.turtlemintdemo.model.Issue
import com.example.turtlemintdemo.model.User
import com.mindorks.framework.mvvm.data.api.ApiHelper



class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getIssuesList(): List<Issue> {
        return apiHelper.getIssuesList()
    }

    suspend fun getCommentList(owner:String,repo:String,commentID:String): List<Comment> {
        return apiHelper.getCommentList(owner,repo,commentID)
    }

}