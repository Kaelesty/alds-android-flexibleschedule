package com.kaelesty.flexibleschedule.presentation.fragments.select

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SelectViewModelFactory(private val context: Context, private val application: Application): ViewModelProvider.Factory {

	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return SelectViewModel(context, application) as T
	}
}