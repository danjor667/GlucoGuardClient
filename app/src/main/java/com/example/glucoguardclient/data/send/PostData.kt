package com.example.glucoguardclient.data.send

data class PostData(
    val pregnancies: Int,
    val Glucose: Int,
    val BloodPressure: Int,
    val SkinThickness: Int,
    val Insulin: Int,
    val BMI: Int,
    val DiabetesPedigreeFunction: Int,
    val Age: Int
){

}
