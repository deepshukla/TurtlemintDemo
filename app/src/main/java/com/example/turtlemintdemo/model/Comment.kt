package com.example.turtlemintdemo.model


import com.google.gson.annotations.SerializedName


    data class Comment(
        @SerializedName("author_association")
        val authorAssociation: String, // COLLABORATOR
        @SerializedName("body")
        val body: String, // @johnshajiang curious if you looked at the above approach? If it's possible then it's probably the best we can do for this experimental use case, until we see more mainstream requirements to allow other protocols. I'll close this in a week or so, as we probably won't make this public API.
        @SerializedName("created_at")
        val createdAt: String, // 2022-05-15T09:57:49Z
        @SerializedName("html_url")
        val htmlUrl: String, // https://github.com/square/okhttp/issues/7272#issuecomment-1126898447
        @SerializedName("id")
        val id: Int, // 1122215263
        @SerializedName("issue_url")
        val issueUrl: String, // https://api.github.com/repos/square/okhttp/issues/7272
        @SerializedName("node_id")
        val nodeId: String, // IC_kwDOAE6eHc5DKxsP
        @SerializedName("performed_via_github_app")
        val performedViaGithubApp: Any, // null
        @SerializedName("reactions")
        val reactions: Reactions,
        @SerializedName("updated_at")
        val updatedAt: String, // 2022-05-15T09:57:49Z
        @SerializedName("url")
        val url: String, // https://api.github.com/repos/square/okhttp/issues/comments/1126898447
        @SerializedName("user")
        val user: User
    ) {
        data class Reactions(
            @SerializedName("confused")
            val confused: Int, // 0
            @SerializedName("eyes")
            val eyes: Int, // 0
            @SerializedName("heart")
            val heart: Int, // 0
            @SerializedName("hooray")
            val hooray: Int, // 0
            @SerializedName("laugh")
            val laugh: Int, // 0
            @SerializedName("rocket")
            val rocket: Int, // 0
            @SerializedName("total_count")
            val totalCount: Int, // 0
            @SerializedName("url")
            val url: String, // https://api.github.com/repos/square/okhttp/issues/comments/1126898447/reactions


        )
    
        data class User(
            @SerializedName("avatar_url")
            val avatarUrl: String, // https://avatars.githubusercontent.com/u/231923?v=4
            @SerializedName("events_url")
            val eventsUrl: String, // https://api.github.com/users/yschimke/events{/privacy}
            @SerializedName("followers_url")
            val followersUrl: String, // https://api.github.com/users/yschimke/followers
            @SerializedName("following_url")
            val followingUrl: String, // https://api.github.com/users/yschimke/following{/other_user}
            @SerializedName("gists_url")
            val gistsUrl: String, // https://api.github.com/users/yschimke/gists{/gist_id}
            @SerializedName("gravatar_id")
            val gravatarId: String,
            @SerializedName("html_url")
            val htmlUrl: String, // https://github.com/yschimke
            @SerializedName("id")
            val id: Int, // 231923
            @SerializedName("login")
            val login: String, // yschimke
            @SerializedName("node_id")
            val nodeId: String, // MDQ6VXNlcjIzMTkyMw==
            @SerializedName("organizations_url")
            val organizationsUrl: String, // https://api.github.com/users/yschimke/orgs
            @SerializedName("received_events_url")
            val receivedEventsUrl: String, // https://api.github.com/users/yschimke/received_events
            @SerializedName("repos_url")
            val reposUrl: String, // https://api.github.com/users/yschimke/repos
            @SerializedName("site_admin")
            val siteAdmin: Boolean, // false
            @SerializedName("starred_url")
            val starredUrl: String, // https://api.github.com/users/yschimke/starred{/owner}{/repo}
            @SerializedName("subscriptions_url")
            val subscriptionsUrl: String, // https://api.github.com/users/yschimke/subscriptions
            @SerializedName("type")
            val type: String, // User
            @SerializedName("url")
            val url: String // https://api.github.com/users/yschimke
        )
    }
