package com.moises.usersrandom.ui.base

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import com.moises.usersrandom.R

abstract class BaseActivity: AppCompatActivity() {

    protected var colorWhite: Int = 0
    private var colorPrimary: Int = 0
    protected var heght: Int = 0
    protected var width: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
        setUpColors()
    }

    private fun setUp() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        heght = displayMetrics.heightPixels
        width = displayMetrics.widthPixels
    }

    private fun setUpColors() {
        colorWhite = ContextCompat.getColor(this, android.R.color.white)
        colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary)
    }
}