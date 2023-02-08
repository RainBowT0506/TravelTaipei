package com.rainbowt.traveltaipei.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.rainbowt.traveltaipei.R
import com.rainbowt.traveltaipei.ui.main.attraction.AttractionFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}