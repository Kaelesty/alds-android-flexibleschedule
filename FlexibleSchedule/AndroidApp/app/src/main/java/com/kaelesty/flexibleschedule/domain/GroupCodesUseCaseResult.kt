package com.kaelesty.flexibleschedule.domain

import com.kaelesty.flexibleschedule.domain.entities.UserGroup

sealed class GroupCodesUseCaseResult

class GroupCodes(
	val groupCodes: List<UserGroup>
): GroupCodesUseCaseResult()

class GroupCodesError(): GroupCodesUseCaseResult()
