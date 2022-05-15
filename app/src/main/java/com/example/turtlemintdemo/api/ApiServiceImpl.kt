package com.mindorks.framework.mvvm.data.api

import com.example.turtlemintdemo.api.RetrofitApi
import com.example.turtlemintdemo.model.Comment
import com.example.turtlemintdemo.model.Issue

import retrofit2.Call
import retrofit2.Retrofit

class ApiServiceImpl : ApiService {

    override suspend fun getIssuesList(): List<Issue> {
         return RetrofitApi.retrofitAPI.getIssuesList()
    }

    override suspend fun getCommentList(owner:String,repo:String,comment_id: String): List<Comment> {
        return RetrofitApi.retrofitAPI.getCommentList(owner,repo,comment_id)
    }


}