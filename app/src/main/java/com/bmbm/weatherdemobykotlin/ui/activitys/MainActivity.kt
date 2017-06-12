package com.bmbm.weatherdemobykotlin.ui.activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.bmbm.weatherdemobykotlin.R
import com.bmbm.weatherdemobykotlin.domain.RequestForecastCommand
import com.bmbm.weatherdemobykotlin.extensions.LongPreferences
import com.bmbm.weatherdemobykotlin.extensions.getContext
import com.bmbm.weatherdemobykotlin.extensions.logd
import com.bmbm.weatherdemobykotlin.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), ToolbarManager {

    override val mToolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    private var mZipCode:Long by LongPreferences(this,SettingActivity.KEY_ZIPCODE,SettingActivity.DEFULT_ZIPCODE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

//        val rv_container = findViewById(R.id.rv_container) as RecyclerView
//        val rv_container: RecyclerView = find(R.id.rv_container) //Anko库中的方法

        rv_container.layoutManager = LinearLayoutManager(getContext())
        attachToScroll(rv_container)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = doAsync {

        val result = RequestForecastCommand(mZipCode).execute()

        logd(msg = result.toString())

        uiThread {

            rv_container.adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(DetailActivity.ID to it.id,
                        DetailActivity.CITY_NAME to result.city)
            }

            toolbarTitle="${result.city}(${result.country})"
        }
    }


}
