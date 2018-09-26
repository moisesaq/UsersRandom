package com.moises.usersrandom.ui.base

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics

open class BaseActivity: AppCompatActivity() {

    protected var colorWhite: Int = 0
    protected var heght: Int = 0
    protected var width: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        colorWhite = ContextCompat.getColor(this, android.R.color.white)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        heght = displayMetrics.heightPixels
        width = displayMetrics.widthPixels
    }
}