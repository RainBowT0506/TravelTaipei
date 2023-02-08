package com.rainbowt.traveltaipei.ui.main.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.rainbowt.traveltaipei.Constants.GOOGLE_URL
import com.rainbowt.traveltaipei.R
import com.rainbowt.traveltaipei.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentWebBinding.inflate(LayoutInflater.from(context))
        binding.webview.loadUrl(if (url == null) GOOGLE_URL else url!!)
        return binding.root
    }

    companion object {
        const val URL = "url"
    }
}