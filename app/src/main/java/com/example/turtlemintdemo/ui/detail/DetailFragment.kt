package com.example.turtlemintdemo.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.turtlemintdemo.R
import com.example.turtlemintdemo.base.ViewModelFactory
import com.example.turtlemintdemo.model.Comment
import com.example.turtlemintdemo.ui.adapter.CommentsAdapter
import com.example.turtlemintdemo.viewmodel.DetailViewModel
import com.mindorks.framework.mvvm.data.api.ApiHelper
import com.mindorks.framework.mvvm.data.api.ApiServiceImpl
import com.mindorks.framework.mvvm.utils.Status


class DetailFragment : Fragment() {
    private var adapter: CommentsAdapter?=null
    private var mCommentTextView: TextView?=null
    private var mProgressBar: ProgressBar?=null
    private var detailViewModel: DetailViewModel? = null
    private var mRecyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_detail, container, false)
        initView(view)
        setupUI()
        setupViewModel()
        setupObserver()
        return view
    }

    private fun initView(view: View) {
        mProgressBar = view.findViewById<ProgressBar>(R.id.progressbar)
        mRecyclerView = view.findViewById<RecyclerView>(R.id.recycler)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment()
    }


    private fun setupUI() {
        mRecyclerView?.layoutManager = LinearLayoutManager(activity)
        adapter = CommentsAdapter(arrayListOf())
        mRecyclerView?.adapter = adapter
    }

    private fun setupObserver() {
        arguments?.getInt("ISSUE_ID")?.let {
            detailViewModel?.getDetails(it.toString())?.observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        mProgressBar?.visibility = View.GONE
                        it.data?.let { comments -> setUI(comments) }
                        mRecyclerView?.visibility = View.VISIBLE
                    }
                    Status.LOADING -> {
                        mProgressBar?.visibility = View.VISIBLE
                        mRecyclerView?.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        //Handle Error
                        mProgressBar?.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }

    private fun setUI(comments: List<Comment>) {
        if (comments.isNotEmpty()) {
           adapter?.addComments(comments)
        }
    }


    private fun setupViewModel() {
        detailViewModel = ViewModelProviders.of(requireActivity(),
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(DetailViewModel::class.java)
    }
}