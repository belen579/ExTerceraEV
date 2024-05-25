package com.example.ex_terceraev.ui.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ex_terceraev.ui.Data.Producto
import com.example.ex_terceraev.ui.Navigation.Screens
import com.example.ex_terceraev.ui.viewmodel.LoginViewModel
import com.example.ex_terceraev.ui.viewmodel.ProductoViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun items(viewModel: ProductoViewModel, navigation: NavController, viewModellogin: LoginViewModel) {

    val scrollState = rememberLazyListState()

    val context = LocalContext.current

    Scaffold(
        topBar={
            TopAppBar(title = { "Productos" }, colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor= MaterialTheme.colorScheme.primary),
                actions= {
                    IconButton(onClick = {  navigation.navigate(route = Screens.portada.route) }) {
                        //  navController.navigate(route= Screens.confirmacion.route)}) {
                        Icon(Icons.Default.Refresh, contentDescription = null, tint = Color.Black)
                    }
                    IconButton(onClick = { viewModel }) {
                        //  navController.navigate(route= Screens.confirmacion.route)}) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color.Black)
                    }
                    IconButton(onClick = { viewModel }) {
                        //  navController.navigate(route= Screens.confirmacion.route)}) {
                        Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Black)
                    }

                    Checkbox(checked = viewModel.checkall, onCheckedChange = { isChecked ->


                        if (isChecked) {
                            viewModel.seleccionartodosloscheck(viewModel.productos)

                        } else {
                            viewModel.cleartodosloscheck()
                        }

                        navigation.navigate(route = Screens.listadoitems.route)


                    })

                }



                )


        }, bottomBar = {
            BottomAppBar(
                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                tonalElevation = 10.dp
            ) {
                Text(
                    text = "Items ${viewModel.numerodeproductos}  Items Seleccionados {${viewModel.contador}}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }





    ) {
        Column(

            modifier= Modifier.padding(top=50.dp)

        ) {
            OutlinedTextField(value = viewModel.nombre,
                onValueChange = { viewModel.setnombre(it)},
                label = { Text("Nombre de item") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            OutlinedTextField(value = viewModel.precio.toString(),
                onValueChange = { viewModel.setprecio(it) },
                label = { Text("Precio") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            Button(onClick = {

                viewModel.añadirproducto(Producto(viewModel.nombre, viewModel.precio, viewModel.seleccion, viewModel.numerodeproductos))
                viewModel.guardarProducto( Producto(viewModel.nombre, viewModel.precio, viewModel.seleccion, viewModel.numerodeproductos),context)

                viewModel.guardarProductos(viewModel.productos, context)



                viewModel.setnombre("")
                viewModel.setprecio("")


            }, modifier = Modifier.padding(5.dp)) {
                Text(text = "Agregar", /*modifier = Modifier.fillMaxWidth()*/)
            }


            Column {
                Text(text = "${viewModel.total}")
            }


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp),
                contentPadding = PaddingValues(bottom = 8.dp),
                state = scrollState
            ) {
                items(viewModel.productos) {
                    listaitems( viewModel,producto=it,
                        editarnombre = {viewModel.setnombre(it)},
                        editarprecio = {viewModel.setprecio(it)}


                    )

                }


            }
        }

    }





}


@Composable
fun listaitems(viewModel: ProductoViewModel, producto: Producto, editarnombre:(String)-> Unit, editarprecio:(String ) -> Unit){
    var checkedstate by rememberSaveable { mutableStateOf(viewModel.listaproductoseleccionado.contains(producto)) }
    val context = LocalContext.current

    Card (modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ){

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()){

            Checkbox(checked =checkedstate , onCheckedChange ={
                checkedstate = it

                if(checkedstate){
                    viewModel.sumarproducto(producto)
                    viewModel.añadircontador()
                }else{
                    viewModel.restarproducto(producto)
                    viewModel.restarcontador()

                }
            }



            )
            Column {
                Text(text = "${producto.nombre}")
                Text(text = "${producto.precio}€")

            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /*viewModel.restarcontador()
                viewModel.decrementarContador(producto)*/
                viewModel.borrarproductos(producto)
            viewModel.eliminarProducto(producto.nombre, context)}) {
                Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Black)
            }


            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { //viewModel.añadircontador()
               // viewModel.incrementarContador(producto)
                editarnombre(producto.nombre)
                editarprecio(producto.precio.toString())
                viewModel.borrarproductos(producto)
                viewModel.eliminarProducto(producto.nombre, context)



               }) {
                Icon(Icons.Default.Edit, contentDescription = null, tint = Color.Black)
            }

        }



    }
}


