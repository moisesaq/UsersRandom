package com.moises.usersrandom.ui.customviews

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class ProfileView: RecyclerView {

    constructor(context: Context): this(context, null) {

    }

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0) {

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):
            super(context, attrs, defStyleAttr) {

    }

    private fun setUp() {
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
    }

}