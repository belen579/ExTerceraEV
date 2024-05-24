package com.example.ex_terceraev.ui.Screens

import android.app.AlertDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ex_terceraev.ui.Data.Usuario
import com.example.ex_terceraev.ui.Data.getListaclassUsuario
import java.io.File


class LoginViewModel(val context: Context) :ViewModel() {

    var showDialog = mutableStateOf(false)
    var usuario by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var usuarios: List<Usuario> by mutableStateOf(emptyList())


    var banderapassword by mutableStateOf(false)


    val nombreArchivo = "Usuarios.dat"


    var banderacambio by mutableStateOf(false)

    var checkedall by mutableStateOf(false)



    fun onConvert() {
        banderacambio = !banderacambio
    }

    var listausuariosseleccionados = mutableStateListOf<Usuario>()


    var listausuarios = mutableStateListOf<Usuario>()

    init {
        listausuarios.addAll(getListaclassUsuario())

    }


    fun ObtenerUsuario(user: String) {
        this.usuario = user
    }

    fun ObtenerPassword(pass: String) {
        this.password = pass
    }


    fun showpassword() {


        banderapassword = !banderapassword
    }


    fun comprobarcontraseña(pass: String, context: Context): Boolean {
        var correcto by mutableStateOf(false)

        if (pass.length < 6) {
            showAlertDialog(context, "Alerta", "La contraseña tiene que tener más de 6 caracteres")
            correcto = false


        } else {

           // showAlertDialog(context, "Alerta", "Puede acceder")
            correcto = true
        }
        return correcto

    }

    fun showAlertDialog(context: Context, title: String, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null) // Botón "OK" sin ninguna acción
        val dialog = builder.create()
        dialog.show()
    }


    fun Guardarusuario(usuario: Usuario) {
        val file = File(context.filesDir, nombreArchivo)
        file.appendText("${usuario.usuario}, ${usuario.contraseña} \n")

        listausuarios.add(usuario)


        println(usuario.toString())
        println("Usuario guardado: ${usuario.usuario}, Contraseña: ${usuario.contraseña}")
        // Mostrar el diálogo después de guardar el usuario
       // showDialog.value = true

    }


    fun ObtenerUsuarios(): List<Usuario> {
        val file = File(context.filesDir, nombreArchivo)
        if (!file.exists()) return emptyList()

        return file.readLines().map {
            val (usuario, password) = it.split(",")
            Usuario(usuario, password, false)

        }

        println(  Usuario(usuario, password, false))

    }




        fun seleccionartodosloscheck(){
            listausuarios.forEachIndexed { index, usuario ->
                listausuarios[index] = usuario.copy(seleccionado = true)
            }

         //   listausuariosseleccionados.addAll(listausuarios)


        }

        fun cleartodosloscheck(){
            listausuarios.forEachIndexed { index, usuario ->
                listausuarios[index] = usuario.copy(seleccionado = false)
            }

          //  listausuariosseleccionados.removeAll(listausuarios)
        }


    fun verificarContraseña(nombre: String, contraseña: String): Boolean {
        val usuario = listausuarios.find { it.usuario == nombre }
        return usuario?.contraseña == contraseña
    }








    fun borrarusuario(usuario:Usuario){
        listausuarios.remove(usuario)
    }

    fun añadirusuario(usuario:Usuario){
        listausuarios.add(usuario)
    }



    fun toggleSeleccion(usuario: Usuario) {
        val index = listausuarios.indexOf(usuario)
        if (index != -1) {
            listausuarios[index] = listausuarios[index].copy(seleccionado = !usuario.seleccionado)
        }
    }


    private fun guardarUsuariosEnArchivo() {
        val file = File(context.filesDir, nombreArchivo)
        file.bufferedWriter().use { out ->
            listausuarios.forEach { usuario ->
                out.write("${usuario.usuario},${usuario.contraseña}\n")
            }
        }
    }




}