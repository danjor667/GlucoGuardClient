package com.example.glucoguardclient.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun FormInputPage(modifier: Modifier = Modifier){

    var Pregnancies by remember { mutableStateOf("") }
    var Glucose by remember { mutableStateOf("") }
    var BloodPressure by remember { mutableStateOf("") }
    var SkinThickness by remember { mutableStateOf("") }
    var Insulin by remember { mutableStateOf("") }
    var BMI by remember { mutableStateOf("") }
    var DiabetesPedigreeFunction by remember { mutableStateOf("") }
    var Age by remember { mutableStateOf("") }



    fun submitForm(): Unit{
        //todo
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column {
            Header(text = "Welcome To GlucoGuard")
        }

        Column(modifier = modifier
            .padding(18.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            CustomTextField(modifier = Modifier
                .fillMaxWidth(),
                label ="pregnancies" ,
                value = Pregnancies,
                onValueChange = {newVale -> Pregnancies = newVale} ,
                hint =138
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))

            CustomTextField(modifier = Modifier
                .fillMaxWidth(),
                label ="glucose" ,
                value = Glucose,
                onValueChange ={newVale -> Glucose = newVale} ,
                hint =138
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))

            CustomTextField(modifier = Modifier
                .fillMaxWidth(),
                label ="blood pressure",
                value = BloodPressure ,
                onValueChange ={newVale -> BloodPressure = newVale} ,
                hint =138
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))

            CustomTextField(modifier = Modifier
                .fillMaxWidth(),
                label ="skin thickness" ,
                value =SkinThickness ,
                onValueChange ={newVale -> SkinThickness = newVale} ,
                hint =138
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))

            CustomTextField(modifier = Modifier
                .fillMaxWidth(),
                label ="insuline" ,
                value = Insulin ,
                onValueChange ={newVale -> Insulin = newVale} ,
                hint =138
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))

            CustomTextField(modifier = Modifier
                .fillMaxWidth(),
                label ="BMI" ,
                value = BMI,
                onValueChange ={newVale -> BMI = newVale} ,
                hint =138
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))

            CustomTextField(modifier = Modifier
                .fillMaxWidth(),
                label ="diabetes Function Value" ,
                value = DiabetesPedigreeFunction ,
                onValueChange ={newVale -> DiabetesPedigreeFunction = newVale} ,
                hint =138
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))

            CustomTextField(modifier = Modifier
                .fillMaxWidth(),
                label ="Age" ,
                value =Age ,
                onValueChange ={newVale -> Age = newVale} ,
                hint =138
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(42.dp))

            MyButton()


        }
    }
}


@Preview
@Composable
fun FormInputPagePreview(){
    FormInputPage()
}









