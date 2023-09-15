package com.kaelesty.flexibleschedule.presentation.fragments.schedule

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaelesty.flexibleschedule.data.repos.GroupRepo
import com.kaelesty.flexibleschedule.domain.entities.Timetable
import com.kaelesty.flexibleschedule.domain.use_cases.GetTimetableUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.UpdateTimetableUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScheduleViewModel(activity: Context, application: Application): AndroidViewModel(application) {

	private val repo = GroupRepo(activity)
	private val getTimetableUseCase = GetTimetableUseCase(repo)
	private val updateTimetableUseCase = UpdateTimetableUseCase(repo)
	
	init {
		viewModelScope.launch(Dispatchers.IO) {
			updateTimetableUseCase.updateTimetable()
		}
	}

	val timetable: LiveData<Timetable> get() = getTimetableUseCase.getTimetable()
}