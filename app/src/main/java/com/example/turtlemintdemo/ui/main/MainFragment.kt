package com.example.turtlemintdemo.ui.main



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.turtlemintdemo.R
import com.example.turtlemintdemo.base.ViewModelFactory
import com.example.turtlemintdemo.model.Issue
import com.example.turtlemintdemo.ui.adapter.MainAdapter
import com.example.turtlemintdemo.utils.ItemClickListener
import com.example.turtlemintdemo.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.data.api.ApiHelper
import com.mindorks.framework.mvvm.data.api.ApiServiceImpl
import com.mindorks.framework.mvvm.utils.Status


class MainFragment : Fragment() {

    private var mainViewModel: MainViewModel? = null
    private var adapter: MainAdapter? = null
    private var mRecyclerView: RecyclerView? = null
    private var mProgressBar: ProgressBar? = null
    private var onRecyclerViewItemClickListener: ItemClickListener? = null
    private var issueList= mutableListOf<Issue>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_main, container, false)
        initView(view)
        setOnItemClickListener()
        setupUI()
        setupViewModel()
        setupObserver()
        return view


    }



    private fun initView(view: View) {
        mRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        mProgressBar = view.findViewById<ProgressBar>(R.id.progressBar)
    }
    private fun setOnItemClickListener() {
        onRecyclerViewItemClickListener = object : ItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val bundle=Bundle()
                bundle.putInt("ISSUE_ID",issueList[position].number)
                findNavController().navigate(R.id.action_mainFragment_to_detailFragment,bundle)
            }
        }
    }

    private fun setupUI() {
        mRecyclerView?.layoutManager = LinearLayoutManager(activity)
        adapter = MainAdapter(arrayListOf(),onRecyclerViewItemClickListener)
        mRecyclerView?.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel?.getIssueLiveData()?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    mProgressBar?.visibility = View.GONE
                    issueList.addAll(it.data as List<Issue>)
                    it.data?.let { issues -> renderList(issues) }
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

    private fun renderList(users: List<Issue>) {
        adapter?.addData(users)
        adapter?.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(requireActivity(),ViewModelFactory(ApiHelper(ApiServiceImpl()))).get(MainViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment()
    }
}