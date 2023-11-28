package com.kaelesty.flexibleschedule.domain.use_cases

import com.kaelesty.flexibleschedule.domain.repo.IGroupRepo

class ConnectGroupUseCase(private val repo: IGroupRepo) {

	suspend fun connectGroup(code: String) = repo.connectGroup(code)
}