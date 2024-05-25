package com.example.ex_terceraev.ui.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ex_terceraev.ui.Data.Producto
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

    var contadorusuarios by mutableStateOf(3)


    fun onConvert() {
        banderacambio = !banderacambio
    }

    var listausuariosseleccionados = mutableStateListOf<Usuario>()


    var listausuarios = mutableStateListOf<Usuario>()

    init {
        listausuarios.addAll(getListaclassUsuario())
        guardarUsuarios(listausuarios, context)

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


    fun comprobarcontraseñaentrelalista(
        usuario: String,
        password: String,
        context: Context
    ): Boolean {
        var acceso by mutableStateOf(false)

        listausuarios.forEach { user ->
            if (user.usuario == usuario && user.contraseña == password) {
                acceso = true
                Toast.makeText(context, "Contraseña correcta", Toast.LENGTH_SHORT).show()

            }


        }

        if(acceso== false){
            Toast.makeText(
                context,
                "Contraseña incorrecta le quedan $contadorusuarios intentos",
                Toast.LENGTH_SHORT
            ).show()
            contadorusuarios--
            if (contadorusuarios == 0) {
                Toast.makeText(context, "No le quedan intentos", Toast.LENGTH_SHORT).show()
            }

        }




        return acceso
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
        listausuarios.add(usuario)
        file.appendText("${usuario.usuario}, ${usuario.contraseña} \n")




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

        println(Usuario(usuario, password, false))

    }


    fun seleccionartodosloscheck() {
        listausuarios.forEachIndexed { index, usuario ->
            listausuarios[index] = usuario.copy(seleccionado = true)
        }

        //   listausuariosseleccionados.addAll(listausuarios)


    }

    fun cleartodosloscheck() {
        listausuarios.forEachIndexed { index, usuario ->
            listausuarios[index] = usuario.copy(seleccionado = false)
        }

        //  listausuariosseleccionados.removeAll(listausuarios)
    }


    fun verificarContraseña(nombre: String, contraseña: String): Boolean {
        val usuario = listausuarios.find { it.usuario == nombre }
        return usuario?.contraseña == contraseña
    }


    fun borrarusuario(usuario: Usuario) {
        listausuarios.remove(usuario)
    }

    fun añadirusuario(usuario: Usuario) {
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


    fun usuarioToString(usuario: Usuario): String {
        return "${usuario.usuario} ${usuario.contraseña}"
    }

    fun guardarUsuarios(usuarios: List<Usuario>, context: Context) {

        var file = File(context.filesDir, nombreArchivo)
        file.printWriter().use { out ->
            listausuarios.forEach { usuario ->
                out.println(usuarioToString(usuario))
            }
        }


    }
}