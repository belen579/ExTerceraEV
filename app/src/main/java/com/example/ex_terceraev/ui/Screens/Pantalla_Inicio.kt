package com.example.ex_terceraev.ui.Screens

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.ex_terceraev.ui.Data.Usuario
import com.example.ex_terceraev.ui.Navigation.Navigation
import com.example.ex_terceraev.ui.Navigation.Screens
import com.example.ex_terceraev.ui.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun login (navController: NavController,viewModel: LoginViewModel){

    var context= LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(value = viewModel.usuario, onValueChange = {it -> viewModel.ObtenerUsuario(it)},
            label = {Text("Usuario")})

        Spacer (modifier = Modifier.padding(10.dp))

        TextField(value = viewModel.password, onValueChange = {it -> viewModel.ObtenerPassword(it)},
            visualTransformation = if(viewModel.banderapassword) VisualTransformation.None else PasswordVisualTransformation(),
            label = {Text("Password")})

        Spacer (modifier = Modifier.padding(10.dp))

        Button(onClick = {if(viewModel.comprobarcontraseñaentrelalista(viewModel.usuario, viewModel.password, context)

            ){
           navController.navigate(route= Screens.listadoitems.route)
          //  navController.navigate(route = Screens.paginaprincipal.route)

        }
                                                                                        }
        ,  modifier = Modifier
                .align(Alignment.CenterHorizontally)

        ) {
            Text(text = "Acceder")
        }


        Box(modifier = Modifier
            .padding(top = 30.dp)
            .padding(start = 10.dp)
            .align(Alignment.Start)
            .clickable { viewModel.showpassword() }){
            Text(text = "Ver contraseña")
        }

        Box(modifier = Modifier
            .padding(top = 30.dp)
            .padding(end = 40.dp)
            .align(Alignment.End)
            .clickable {
                navController.navigate(route= Screens.registro.route)
              //  viewModel.Guardarusuario(
                //    Usuario(viewModel.usuario, viewModel.password)
              //  )
            }){
            Text(text = "Registrarse")
        }


    }



    }



@Preview
@Composable
fun previewparaver(){
    var context= LocalContext.current
    var viewmodel = LoginViewModel(context)
    var navController = rememberNavController()
    registrarse(navController,viewmodel)

}


