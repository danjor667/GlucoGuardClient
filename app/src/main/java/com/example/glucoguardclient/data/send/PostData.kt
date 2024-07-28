package com.example.glucoguardclient.data.send

data class PostData(
    val pregnancies: Int,
    val Glucose: Double,
    val BloodPressure: Double,
    val SkinThickness: Double,
    val Insulin: Double,
    val BMI: Double,
    val DiabetesPedigreeFunction: Double,
    val Age: Int
){

}
