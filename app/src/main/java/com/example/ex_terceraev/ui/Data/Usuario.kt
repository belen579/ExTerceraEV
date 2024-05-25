package com.example.ex_terceraev.ui.Data

data class Usuario(

    var usuario: String,
    var contraseña: String,
    var seleccionado: Boolean

) {

}


var listausuarios= mutableListOf<Usuario>(
    Usuario("Ana", "123456", false),
    Usuario("Carmen", "123456", false),
    Usuario("Alex", "123456", false),
    Usuario("Maria", "123456", false)


)

fun getListaclassUsuario(): MutableList<Usuario> {
    return listausuarios
}