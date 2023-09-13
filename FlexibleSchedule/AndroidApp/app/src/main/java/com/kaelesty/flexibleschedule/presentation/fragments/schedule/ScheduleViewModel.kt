package com.kaelesty.flexibleschedule.presentation.fragments.schedule

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaelesty.flexibleschedule.data.GroupRepo
import com.kaelesty.flexibleschedule.domain.entities.Timetable
import com.kaelesty.flexibleschedule.domain.use_cases.GetTimetableUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScheduleViewModel(activity: Context, application: Application): AndroidViewModel(application) {

	private val repo = GroupRepo(activity)
	private val getTimetableUseCase = GetTimetableUseCase(repo)

	private val _timetable: MutableLiveData<Timetable> = MutableLiveData()
	val timetable: LiveData<Timetable> get() = _timetable

	init {
		viewModelScope.launch(Dispatchers.IO) {
			val timetab = repo.getTimetable()
			_timetable.postValue(timetab)
		}
	}
}