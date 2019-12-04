package com.example.a71800662.healthhistoryv1.services

import com.example.a71800662.healthhistoryv1.models.Account
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceEvent {

    @POST ( "calendars/calendarId/events")
    fun createCalendarEvent (@Body account: Account) : Call<Account>

}