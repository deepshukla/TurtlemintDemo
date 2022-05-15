package com.mindorks.framework.mvvm.data.api


import com.example.turtlemintdemo.model.Comment
import com.example.turtlemintdemo.model.Issue
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("repos/square/okhttp/issues")
    suspend fun getIssuesList(): List<Issue>

    @GET("repos/{owner}/{repo}/issues/{comment_id}/comments")
    suspend fun getCommentList(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("comment_id") comment_id: String
    ): List<Comment>

}