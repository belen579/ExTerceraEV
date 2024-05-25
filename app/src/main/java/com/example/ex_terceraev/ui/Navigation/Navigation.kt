package com.example.ex_terceraev.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ex_terceraev.ui.Screens.items
import com.example.ex_terceraev.ui.Screens.login
import com.example.ex_terceraev.ui.Screens.loginlistado
import com.example.ex_terceraev.ui.Screens.presentacionScafold
import com.example.ex_terceraev.ui.Screens.registrarse
import com.example.ex_terceraev.ui.Screens.scaffold
import com.example.ex_terceraev.ui.viewmodel.LoginViewModel
import com.example.ex_terceraev.ui.viewmodel.ProductoViewModel


@Composable
fun Navigation(){
    val navController = rememberNavController()
    val context = LocalContext.current//linea para recordar NavController entre pantallas
    val viewModel= remember{ LoginViewModel(context) }
    val viewModelProducto = remember {
        ProductoViewModel()
    }//linea para recordar viewModel entre pantallas
    NavHost(navController, startDestination = Screens.portada.route) {
        //pantalla principal con la navegación
        composable(route = Screens.portada.route) {login( navController,viewModel) }//Nombre del fichero .kt al que navegar
       composable(route = Screens.registro.route) {
            registrarse(navController,viewModel) //Nombre de la función composable a la que navegar
        }
/*
     composable(route = Screens.listadoitems.route) {
         items(viewModelProducto,navController,viewModel  ) //Nombre de la función composable a la que navegar
        }

*/

        composable(route = Screens.paginaprincipal.route) {
            scaffold(viewModelProducto,navController ) //Nombre de la función composable a la que navegar
        }
    }
}