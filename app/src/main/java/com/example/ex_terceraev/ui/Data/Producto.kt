package com.example.ex_terceraev.ui.Data

import java.io.Serializable

data class Producto(
    var nombre: String,
    var precio: String,
    var seleccionado: Boolean = false,
    var numerodeproductos:Int,




    ): Serializable

var listaproductos = mutableListOf<Producto>(





 /*
    Producto("Raton", 20.0, false,0 ),
    Producto("Pantalla", 80.0, false,0),
    Producto("RAM", 50.0, false,0),
    Producto("CPU", 300.0, false,0),
    Producto("Camara", 20.0, false,0),
    Producto("Tarjeta Grafica", 100.0, false,0),
    Producto("Batería", 70.0, false,0),
    Producto("CPU", 300.0, false,0),
    Producto("Television", 20.0, false,0),
    Producto("Antena", 100.0, false,0),
    Producto("Radio", 70.0, false,0)*/

)



fun getListaclass(): MutableList<Producto> {


    for (i in 1..5) {
        listaproductos.add(Producto("Producto $i",  "${i*10}", false,0))
    }
    return listaproductos
}






