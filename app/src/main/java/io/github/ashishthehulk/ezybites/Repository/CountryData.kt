package io.github.ashishthehulk.ezybites

import com.google.gson.annotations.SerializedName
import java.util.*


data class CountryData (

  @SerializedName("meals" ) var meals : ArrayList<Meals> = arrayListOf()


)