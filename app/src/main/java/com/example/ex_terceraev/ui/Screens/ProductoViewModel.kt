package com.example.ex_terceraev.ui.Screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.ex_terceraev.ui.Data.Producto
import com.example.ex_terceraev.ui.Data.getListaclass

class ProductoViewModel: ViewModel() {


    var checkproducto by mutableStateOf(false)


    var nombre by mutableStateOf("")


    var total by mutableStateOf(0.0)

    var checkall by mutableStateOf(false)

    var listaproductoseleccionado = mutableStateListOf<Producto>()

    var productos = mutableStateListOf<Producto>()


    var contadorproducto by mutableStateOf(0)





    var contador by mutableStateOf(0)


    fun getLista(): MutableList<Producto> {
        return  getListaclass()
    }




    init {
        productos.addAll(getListaclass())




        //   crearListaDeProductos()
    }

    var numerodeproductos by mutableStateOf(productos.size)




        // Esto puede ser List o listOf ()

          /*  return List(20) { index ->
                Producto("Producto ${index + 1}", 0.0,false)
            }*/


/*
    fun crearListaDeProductos(): List<Producto> {


        for (i in 1..30) {
            lista.add(Producto("Producto $i", precio = (i * 10).toDouble()))
        }

        return lista
    }*/



    fun sumarproducto(producto: Producto){

        total+= producto.precio;



    }

    fun restarproducto(producto: Producto){

        total-= producto.precio;


//
    }


    fun seleccionartodosloscheck(productos:List<Producto>){

        listaproductoseleccionado.addAll(productos)
        total = listaproductoseleccionado.sumOf { it.precio }
        println(listaproductoseleccionado.joinToString())

    }

    fun cleartodosloscheck(){

            listaproductoseleccionado.removeAll(productos)
        total =0.0
        println(listaproductoseleccionado.joinToString())
    }


    fun borrarproductos(producto:Producto){

       productos.remove(producto)
    }


    fun añadirproducto(producto: Producto){
        productos.add(producto)
    }

    fun modificar(producto: Producto){

    }

    fun añadircontador() {
        contador++


    }

    fun restarcontador(){
        contador--


    }

    fun añadircontadorproducto(producto: Producto){
        producto.numerodeproductos++
    }

    fun restarcontadorproducto(producto: Producto){
         producto.numerodeproductos--
    }

    fun getnumeroproductos(producto: Producto): Int {
        return producto.numerodeproductos
    }


    fun incrementarContador(producto: Producto) {
        val index = productos.indexOf(producto)
        if (index != -1) {
            val nuevoNumero = productos[index].numerodeproductos + 1
            productos[index] = productos[index].copy(numerodeproductos = nuevoNumero)
        }
    }

    fun decrementarContador(producto: Producto) {
        val index = productos.indexOf(producto)
        if (index != -1 && productos[index].numerodeproductos > 0) {
            val nuevoNumero = productos[index].numerodeproductos - 1
            productos[index] = productos[index].copy(numerodeproductos = nuevoNumero)
        }
    }

}





