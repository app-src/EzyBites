package io.github.ashishthehulk.ezybites

import com.google.gson.annotations.SerializedName


data class Meals (

  @SerializedName("strMeal"      ) var strMeal      : String? = null,
  @SerializedName("strMealThumb" ) var strMealThumb : String? = null,
  @SerializedName("idMeal"       ) var idMeal       : String? = null

)