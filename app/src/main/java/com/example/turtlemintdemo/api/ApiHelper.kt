package com.mindorks.framework.mvvm.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getIssuesList() = apiService.getIssuesList()
    suspend fun getCommentList(owner:String,repo:String,issueId:String) = apiService.getCommentList(owner,repo,issueId)

}