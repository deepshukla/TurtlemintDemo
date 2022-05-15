package com.example.turtlemintdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turtlemintdemo.model.Comment
import com.example.turtlemintdemo.model.Issue
import com.example.turtlemintdemo.repository.MainRepository
import com.mindorks.framework.mvvm.utils.Resource
import kotlinx.coroutines.launch


class DetailViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val data = MutableLiveData<Resource<List<Comment>>>()



    override fun onCleared() {
        super.onCleared()
    }

    fun getDetails(commentID:String): LiveData<Resource<List<Comment>>> {
        data.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                val commentList = mainRepository.getCommentList("square","okhttp",commentID)
                data.postValue(Resource.success(commentList))
            } catch (e: Exception) {
                data.postValue(Resource.error("Something Went Wrong", null))
            }

        }
        return data
    }

}