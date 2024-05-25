package com.example.ex_terceraev.ui.Screens


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.ex_terceraev.ui.Data.Usuario
import com.example.ex_terceraev.ui.Navigation.Screens
import com.example.ex_terceraev.ui.viewmodel.LoginViewModel

@Composable
fun GuardarUsuarioButton(viewModel: LoginViewModel, navController: NavController) {
    var showDialog by remember { viewModel.showDialog }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Usuario Guardado") },
            text = { Text("El usuario ha sido guardado exitosamente.") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    navController.navigate(route = Screens.paginaprincipal.route)
                }) {
                    Text("Aceptar")
                }
            }
        )
    }

  /*  Button(
        onClick = {
            // Llamar a la función Guardarusuario
            viewModel.Guardarusuario(Usuario(viewModel.usuario, viewModel.password))
        },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        Text(text = "Guardar")
    }*/
}

