package io.github.ashishthehulk.ezybites

import com.google.gson.annotations.SerializedName


data class ItemData (

  @SerializedName("meals" ) var meals : ArrayList<Mealss> = arrayListOf()

)