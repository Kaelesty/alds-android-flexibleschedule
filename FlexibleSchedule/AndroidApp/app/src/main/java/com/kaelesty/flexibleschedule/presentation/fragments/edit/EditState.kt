package com.kaelesty.flexibleschedule.presentation.fragments.edit

sealed class EditState

class EditStateOK: EditState()

class EditStateUnauth: EditState()

class EditStateToMany: EditState()

class EditStateUnknown: EditState()
