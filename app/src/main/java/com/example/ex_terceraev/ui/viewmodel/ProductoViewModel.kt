package com.example.ex_terceraev.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.ex_terceraev.ui.Data.Producto
import com.example.ex_terceraev.ui.Data.getListaclass
import java.io.EOFException
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.System.out


class ProductoViewModel: ViewModel() {


    var checkproducto by mutableStateOf(false)


    var nombre by mutableStateOf("")

    var precio by mutableStateOf("")


    var total by mutableStateOf(0.0)

    var checkall by mutableStateOf(false)

    var listaproductoseleccionado = mutableStateListOf<Producto>()

    var productos = mutableStateListOf<Producto>()


    var contadorproducto by mutableStateOf(0)

    var seleccion by mutableStateOf(false)


    var contador by mutableStateOf(0)


    fun setnombre(nombre: String) {
        this.nombre = nombre
    }

    fun setprecio(precio: String) {
        this.precio = precio
    }

    fun setseleccionado(seleccion: Boolean) {
        this.seleccion = seleccion


    }

    fun setnumeroproductos(numeroproductos: Int) {
        this.numerodeproductos = numerodeproductos

    }


    fun getLista(): MutableList<Producto> {
        return getListaclass()
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


   /* fun sumarproducto(producto: Producto) {

        total += producto.precio.toDouble();


    }*/

 /*   fun restarproducto(producto: Producto) {

        total -= producto.precio.toDouble();


    }*/


    fun seleccionartodosloscheck(productos: List<Producto>) {

        listaproductoseleccionado.addAll(productos)
      //  total = listaproductoseleccionado.sumOf { it.precio.toDouble() }
        contador = listaproductoseleccionado.size
        println(listaproductoseleccionado.joinToString())
        checkall = true

    }

    fun cleartodosloscheck() {

        listaproductoseleccionado.removeAll(productos)
      //  total = 0.0
        contador = listaproductoseleccionado.size
        println(listaproductoseleccionado.joinToString())
        checkall = false
    }


    fun borrarproductos(producto: Producto) {

        productos.remove(producto)
        productos.sortBy { it.nombre.count() }
    }


    fun añadirproducto(producto: Producto) {
        productos.add(producto)
        productos.sortBy { it.nombre.count() }
    }

    fun modificar(producto: Producto) {


    }

    fun añadircontador() {
        contador++


    }

    fun restarcontador() {
        contador--


    }

    fun añadircontadorproducto(producto: Producto) {
        producto.numerodeproductos++
    }

    fun restarcontadorproducto(producto: Producto) {
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


// Guardar y eliminar en fichero con texto:

    var nombreArchivo = "Productos.dat"

    fun productoToString(producto: Producto): String {
        return "${producto.nombre}|${producto.precio}|${producto.seleccionado}|${producto.numerodeproductos}"
    }

    // Convierte una línea de texto a un producto
    fun stringToProducto(string: String): Producto {
        val parts = string.split("|")
        return Producto(
            nombre = parts[0],
            precio = parts[1],
            seleccionado = parts[2].toBoolean(),
            numerodeproductos = parts[3].toInt()
        )
    }

    // Guarda la lista de productos en un archivo de texto
    fun guardarProductos(productos: List<Producto>, context: Context) {

        var file = File(context.filesDir, nombreArchivo)
        file.printWriter().use { out ->
            productos.forEach { producto ->
                out.println(productoToString(producto))
            }
        }
    }

    fun guardarProducto(producto: Producto, context: Context) {
        var file = File(context.filesDir, nombreArchivo)
        file.printWriter().use {
            out.println(productoToString(producto))
        }
    }


    // Carga la lista de productos desde un archivo de texto
    fun cargarProductos(file: File): List<Producto> {
        if (!file.exists()) return emptyList()
        return file.readLines().map { stringToProducto(it) }
    }


// Elimina un producto del archivo de texto


    fun eliminarProducto(nombre: String, context: Context) {
        var file = File(context.filesDir, nombreArchivo)

        val productos = cargarProductos(file).toMutableList()
        val productoAEliminar = productos.find { it.nombre == nombre }
        productoAEliminar?.let {
            productos.remove(it)
            guardarProductos(productos, context)
        }
    }
}



