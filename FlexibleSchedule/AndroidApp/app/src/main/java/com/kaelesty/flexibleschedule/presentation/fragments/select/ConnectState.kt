package com.kaelesty.flexibleschedule.presentation.fragments.select

sealed class ConnectState

class ConnectOK(): ConnectState()

class ConnectUnauth(): ConnectState()

class ConnectNotfound(): ConnectState()

class ConnectUnknown(): ConnectState()