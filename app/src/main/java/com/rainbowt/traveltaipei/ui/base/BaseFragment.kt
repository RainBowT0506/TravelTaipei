package com.rainbowt.traveltaipei.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rainbowt.traveltaipei.data.api.RemoteDataSource
import com.rainbowt.traveltaipei.data.repository.BaseRepository
import com.rainbowt.traveltaipei.ui.main.MainActivity
import com.rainbowt.traveltaipei.viewmodel.ViewModelFactory

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> : Fragment() {

    protected lateinit var binding: B
    protected lateinit var viewModel: VM

    var remoteDataSource = RemoteDataSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ViewModelFactory(getRepository())
        viewModel = ViewModelProvider(this, factory)[getViewModel()]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        initToolBar()
        initViews()
        return binding.root
    }

    abstract fun initToolBar()
    abstract fun initViews()

    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): Class<VM>

    abstract fun getRepository(): BaseRepository

    fun setTitleText(title: CharSequence, resColor: Int) {
        (activity as MainActivity).setTitleText(title, resColor)
    }

    fun hideLeftContentIcon(){
        (activity as MainActivity).hideLeftContentIcon()
    }
    fun setLeftContentIcon(res: Int , onClick:()->Unit) {
        (activity as MainActivity).setLeftContentIcon(res,onClick)
    }
}