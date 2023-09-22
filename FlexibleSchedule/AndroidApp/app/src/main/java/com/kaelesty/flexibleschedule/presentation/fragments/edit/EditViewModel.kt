package com.kaelesty.flexibleschedule.presentation.fragments.edit

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaelesty.flexibleschedule.data.repos.GroupRepo
import com.kaelesty.flexibleschedule.domain.GroupReturnCode
import com.kaelesty.flexibleschedule.domain.entities.Timetable
import com.kaelesty.flexibleschedule.domain.use_cases.UploadTimetableUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditViewModel(activity: Context, application: Application): AndroidViewModel(application) {

	private val repo = GroupRepo(activity)
	private val uploadTimetableUseCase = UploadTimetableUseCase(repo)

	private val _state = MutableLiveData<EditState>()
	val state: LiveData<EditState> get() = _state

	fun upload(name: String, timetable: Timetable) {
		viewModelScope.launch(Dispatchers.IO) {
			val rc = uploadTimetableUseCase.upload(name, timetable)

			when (rc) {
				GroupReturnCode.RC_UPLOAD_OK -> _state.postValue(EditStateOK())
				GroupReturnCode.RC_UPLOAD_TO_MANY -> _state.postValue(EditStateToMany())
				GroupReturnCode.RC_UPLOAD_UNAUTH -> _state.postValue(EditStateUnauth())
				else -> _state.postValue(EditStateUnknown())
			}
		}
	}
}