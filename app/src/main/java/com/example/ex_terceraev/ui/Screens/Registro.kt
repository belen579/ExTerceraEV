package com.example.ex_terceraev.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ex_terceraev.ui.Data.Usuario
import com.example.ex_terceraev.ui.Navigation.Screens
import com.example.ex_terceraev.ui.viewmodel.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun registrarse(navController: NavController, viewModel: LoginViewModel){

    var context= LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(modifier = Modifier
            .padding(top = 30.dp)
            .align(Alignment.CenterHorizontally)
        ){
            Text(text = "Registrarse")
        }
        Spacer (modifier = Modifier.padding(10.dp))

        TextField(value = viewModel.usuario, onValueChange = {it -> viewModel.ObtenerUsuario(it)},
            label = { Text("Usuario") })

        Spacer (modifier = Modifier.padding(10.dp))

        TextField(value = viewModel.password, onValueChange = {it -> viewModel.ObtenerPassword(it)},
            visualTransformation = if(viewModel.banderapassword) VisualTransformation.None else PasswordVisualTransformation(),
            label = { Text("Password") })

        Spacer (modifier = Modifier.padding(10.dp))

        Button(onClick = {
            if(viewModel.comprobarcontraseña(viewModel.password, context)) {
                viewModel.Guardarusuario(
                    Usuario(viewModel.usuario, viewModel.password, false)
                )
                viewModel.showDialog


                navController.navigate(route = Screens.portada.route)


            }

        },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

        ) {
            Text(text = "Guardar")
        }



    }


}



