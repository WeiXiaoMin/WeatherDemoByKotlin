package com.bmbm.weatherdemobykotlin.ui.activitys

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.bmbm.weatherdemobykotlin.R
import com.bmbm.weatherdemobykotlin.extensions.ctx
import com.bmbm.weatherdemobykotlin.extensions.slideEnter
import com.bmbm.weatherdemobykotlin.extensions.slideExit
import org.jetbrains.anko.startActivity

/**
 * Created by wxm on 2017/6/11.
 */
interface ToolbarManager {

    val mToolbar: Toolbar

    var toolbarTitle: String
        get() = mToolbar.title.toString()
        set(value) {
            mToolbar.title = value
        }

    fun initToolbar() {
        mToolbar.inflateMenu(R.menu.menu_main)
        mToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_setting -> mToolbar.ctx.startActivity<SettingActivity>()
            }

            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        mToolbar.navigationIcon = createUpDrawable()
        mToolbar.setNavigationOnClickListener { up() }
    }


    private fun createUpDrawable() = DrawerArrowDrawable(mToolbar.ctx).apply { progress = 1f }


    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) mToolbar.slideExit() else mToolbar.slideEnter()
            }
        })
    }


}