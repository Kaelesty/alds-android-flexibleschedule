package com.kaelesty.flexibleschedule.domain

enum class GroupReturnCode {
	RC_UPLOAD_OK, 		// http 200
	RC_UPLOAD_TO_MANY, 	// http 400, when user create timetable it will automatically add to user's timetable list,
	//							     but user cant have >2 timetables in list.
	RC_UPLOAD_UNAUTH, 	// http 401, unauthorized
	RC_UPLOAD_UNKWOWN	// all other http rcs
}