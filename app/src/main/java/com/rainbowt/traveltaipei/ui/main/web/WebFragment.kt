package com.rainbowt.traveltaipei.ui.main.web

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import com.rainbowt.traveltaipei.Constants.GOOGLE_URL
import com.rainbowt.traveltaipei.R
import com.rainbowt.traveltaipei.databinding.FragmentWebBinding
import com.rainbowt.traveltaipei.ui.main.detail.DetailFragment

class WebFragment : Fragment() {

    lateinit var binding : FragmentWebBinding
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentWebBinding.inflate(LayoutInflater.from(context))
        var url = if (url == null) GOOGLE_URL else url!!
        Logger.d(TAG + url)
        initWebView(url)
        return binding.root
    }

    private fun initWebView(url: String) {
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.webChromeClient = getWebChromeClient()
        binding.webview.webViewClient = getWebViewClient()
        binding.webview.loadUrl(url)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.webview.destroy()
    }

    private fun getWebViewClient(): WebViewClient {
        return object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressbar.visibility = View.VISIBLE;
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressbar.visibility = View.GONE;
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url == null) return false
                try {
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        return true
                    }
                } catch (e: Exception) {
                    return true
                }
                view.loadUrl(url)
                return true
            }
        }
    }

    private fun getWebChromeClient(): WebChromeClient {
        return object : WebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                binding.progressbar.progress = newProgress;
            }
        }
    }

    companion object {
        val TAG = WebFragment::class.java.toString()
        const val URL = "url"
    }
}