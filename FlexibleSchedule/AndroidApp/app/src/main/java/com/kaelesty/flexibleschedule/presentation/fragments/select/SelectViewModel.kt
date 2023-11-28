package com.kaelesty.flexibleschedule.presentation.fragments.select

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaelesty.flexibleschedule.data.repos.GroupRepo
import com.kaelesty.flexibleschedule.domain.ConnectGroupReturnCode
import com.kaelesty.flexibleschedule.domain.GroupCodes
import com.kaelesty.flexibleschedule.domain.entities.UserGroup
import com.kaelesty.flexibleschedule.domain.use_cases.ChangePriorityUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.ConnectGroupUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.DeleteGroupUseCase
import com.kaelesty.flexibleschedule.domain.use_cases.GetAllGroupCodesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectViewModel(activity: Context, application: Application): AndroidViewModel(application) {

	private val repo = GroupRepo(activity)
	private val connectGroupUseCase = ConnectGroupUseCase(repo)
	private val getAllGroupCodesUseCase = GetAllGroupCodesUseCase(repo)
	private val changePriorityUseCase = ChangePriorityUseCase(repo)
	private val deleteGroupUseCase = DeleteGroupUseCase(repo)

	private val _connectState: MutableLiveData<ConnectState> = MutableLiveData()
	val connectState: LiveData<ConnectState> get() = _connectState

	private val _groups: MutableLiveData<List<UserGroup>> = MutableLiveData()
	val groups: LiveData<List<UserGroup>> get() = _groups

	init {
		loadGroupCodes()
	}

	fun loadGroupCodes() {
		viewModelScope.launch(Dispatchers.IO) {
			val result = getAllGroupCodesUseCase.getAllGroupCodesUseCase()
			_groups.postValue(
				when (result) {
					is GroupCodes -> {
						result.groupCodes
					}
					else -> {
						listOf()
					}
				}
			)
		}
	}

	fun deleteGroup(userGroup: UserGroup) {
		viewModelScope.launch(Dispatchers.IO) {
			deleteGroupUseCase.deleteGroup(userGroup)
			loadGroupCodes()
		}
	}

	fun changePriority(userGroup: UserGroup) {
		viewModelScope.launch(Dispatchers.IO) {
			changePriorityUseCase.changePriority(userGroup)
			loadGroupCodes()
		}
	}

	fun connectGroup(code: String) {
		viewModelScope.launch(Dispatchers.IO) {
			when(connectGroupUseCase.connectGroup(code)) {
				ConnectGroupReturnCode.GC_OK -> _connectState.postValue(ConnectOK())
				ConnectGroupReturnCode.GC_UNAUTH -> _connectState.postValue(ConnectUnauth())
				ConnectGroupReturnCode.GC_NOTFOUND -> _connectState.postValue(ConnectNotfound())
				ConnectGroupReturnCode.GC_UNKNOWN -> _connectState.postValue(ConnectUnknown())
			}
			loadGroupCodes()
		}
	}
}