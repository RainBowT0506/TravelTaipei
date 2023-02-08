package com.rainbowt.traveltaipei.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rainbowt.traveltaipei.R
import com.rainbowt.traveltaipei.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tw -> return super.onOptionsItemSelected(item)
            R.id.cn -> return super.onOptionsItemSelected(item)
            R.id.en -> return super.onOptionsItemSelected(item)
            R.id.ja -> return super.onOptionsItemSelected(item)
            R.id.ko -> return super.onOptionsItemSelected(item)
            R.id.es -> return super.onOptionsItemSelected(item)
            R.id.id -> return super.onOptionsItemSelected(item)
            R.id.th -> return super.onOptionsItemSelected(item)
            R.id.vi -> return super.onOptionsItemSelected(item)
            else -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 1) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    fun setTitleText(title: CharSequence, resColor: Int) {
        binding.layoutToolbar.tvTitle.text = title
        binding.layoutToolbar.tvTitle.setTextColor(resColor)
        binding.layoutToolbar.tvTitle.visibility = View.VISIBLE
        binding.layoutToolbar.ivTitle.visibility = View.GONE
    }

    fun showLeftContentIcon(res: Int, onClick: () -> Unit) {
        binding.layoutToolbar.leftIcon.setImageResource(res)
        binding.layoutToolbar.leftIcon.visibility = View.VISIBLE
        binding.layoutToolbar.leftIcon.setOnClickListener {
            onClick.invoke()
        }
    }

    fun hideLeftContentIcon() {
        binding.layoutToolbar.leftIcon.setImageResource(0)
        binding.layoutToolbar.leftIcon.visibility = View.INVISIBLE
        binding.layoutToolbar.leftIcon.setOnClickListener(null)
    }

    fun showRightContentIcon(res: Int, onClick: () -> Unit) {
        binding.layoutToolbar.rightIcon.setImageResource(res)
        binding.layoutToolbar.rightIcon.visibility = View.VISIBLE
        binding.layoutToolbar.rightIcon.setOnClickListener {
            onClick.invoke()
        }
    }

    fun hideRightContentIcon() {
        binding.layoutToolbar.rightIcon.setImageResource(0)
        binding.layoutToolbar.rightIcon.visibility = View.INVISIBLE
        binding.layoutToolbar.rightIcon.setOnClickListener(null)
    }

}