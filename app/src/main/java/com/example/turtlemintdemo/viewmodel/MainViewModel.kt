package com.example.turtlemintdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turtlemintdemo.model.Issue
import com.example.turtlemintdemo.repository.MainRepository
import com.mindorks.framework.mvvm.utils.Resource
import kotlinx.coroutines.launch


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val data = MutableLiveData<Resource<List<Issue>>>()


    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        data.postValue(Resource.loading(null))
        viewModelScope.launch {
            try {
                val userList = mainRepository.getIssuesList()
                data.postValue(Resource.success(userList))
            } catch (e: Exception) {
                data.postValue(Resource.error("Something Went Wrong", null))
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun getIssueLiveData(): LiveData<Resource<List<Issue>>> {
        return data
    }

}