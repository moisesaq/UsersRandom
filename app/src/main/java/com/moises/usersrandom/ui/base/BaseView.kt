package com.moises.usersrandom.ui.base

interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun showError(error: String)
}