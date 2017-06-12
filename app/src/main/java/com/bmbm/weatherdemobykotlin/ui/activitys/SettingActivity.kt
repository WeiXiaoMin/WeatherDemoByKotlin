package com.bmbm.weatherdemobykotlin.ui.activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bmbm.weatherdemobykotlin.R
import com.bmbm.weatherdemobykotlin.extensions.LongPreferences
import com.bmbm.weatherdemobykotlin.extensions.logi
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.view_toolbar.*

class SettingActivity : AppCompatActivity() {

    var mZipCode: Long by LongPreferences(this, KEY_ZIPCODE, DEFULT_ZIPCODE)

    companion object {
        val KEY_ZIPCODE = "zipCode"
        val DEFULT_ZIPCODE = 94043L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        logi("title=$title")
    }

    override fun onResume() {
        super.onResume()

        et_zipCode.setText(mZipCode.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val text = et_zipCode.text.trim().toString()
        if (text.isNotEmpty()) mZipCode = text.toLong()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean=when(item.itemId){
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }
}
