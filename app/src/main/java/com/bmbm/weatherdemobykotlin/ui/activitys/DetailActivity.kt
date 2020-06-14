package com.bmbm.weatherdemobykotlin.ui.activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.bmbm.weatherdemobykotlin.R
import com.bmbm.weatherdemobykotlin.domain.model.Forecast
import com.bmbm.weatherdemobykotlin.domain.model.RequestDayForecastCommand
import com.bmbm.weatherdemobykotlin.extensions.*
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.themedToolbar
import java.text.DateFormat


class DetailActivity : AppCompatActivity(), ToolbarManager {

    private lateinit var icon: ImageView
    private lateinit var weatherDescription: TextView
    private lateinit var maxTemperature: TextView
    private lateinit var minTemperature: TextView
    private lateinit var myToolbar:Toolbar
     override val mToolbar: Toolbar by lazy { myToolbar }

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)

        verticalLayout {

            myToolbar = themedToolbar(android.support.v7.appcompat.R.style.ThemeOverlay_AppCompat_Dark_ActionBar) {
                val bgTv = typedValue(android.support.v7.appcompat.R.attr.colorPrimary)
                backgroundColor = bgTv.data
                popupTheme = android.support.v7.appcompat.R.style.ThemeOverlay_AppCompat_Light
            }.lparams(width = matchParent) {
                //wxm : 使用默认值即可
                val toolbarTv = typedValue(android.support.v7.appcompat.R.attr.actionBarSize)
                Log.d(this@DetailActivity.javaClass.simpleName, toolbarTv.toString())
                height = TypedValue.complexToDimensionPixelSize(toolbarTv.data, displayMetrics)
            }

            linearLayout {
                leftPadding = dimen(R.dimen.spacing_xlarge)
                rightPadding = dimen(R.dimen.spacing_xlarge)
                topPadding = dimen(R.dimen.spacing_xlarge)
                gravity = Gravity.CENTER_VERTICAL

                icon = imageView(R.mipmap.ic_launcher) {
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }.lparams(dip(64), dip(64))

                weatherDescription = textView("description") {

                    setAppearance(android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Display1)

                }.lparams {
                    margin = dimen(R.dimen.spacing_xlarge)
                }


            }.lparams(matchParent) {
                leftMargin = dimen(R.dimen.spacing_xlarge)
                rightMargin = dimen(R.dimen.spacing_xlarge)
            }


            linearLayout {

                maxTemperature = textView("maxTemperature") {
                    gravity = Gravity.CENTER_HORIZONTAL
                    setAppearance(android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Display3)

                }.lparams(width = 0, weight = 1f) {
                    margin = dimen(R.dimen.spacing_xlarge)
                }

                minTemperature = textView("minTemperature") {
                    gravity = Gravity.CENTER_HORIZONTAL
                    setAppearance(android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Display3)

                }.lparams(width = 0, weight = 1f) {
                    margin = dimen(R.dimen.spacing_xlarge)
                }

            }.lparams(width = matchParent)
        }

        initToolbar()
        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }

        doAsync {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()

            runOnUiThread {
                bindForecast(result)
            }
        }

    }

    fun bindForecast(result: Forecast) = with(result) {
        Picasso.with(ctx).load(iconUrl).into(icon)
        weatherDescription.text = description
        mToolbar.subtitle = date.toDateString(DateFormat.FULL)

        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}°"
        it.second.textColor = color(when (it.first) {
            in -15 until 0 -> android.R.color.holo_blue_light
            in 0 until 15 -> android.R.color.holo_blue_dark
            in 15 until 25 -> android.R.color.holo_green_dark
            in 25 until 35 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_red_dark
        })
    }
}
