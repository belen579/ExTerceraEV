package com.example.ex_terceraev.ui.Navigation

sealed class  Screens(val route: String) {

    object  portada: Screens("Pantalla_Inicio")

    object  registro: Screens("Registro")
    object  paginaprincipal: Screens("Paginaprincipal")

    object activityunica : Screens ("Login_Listado")
}