package com.example.ex_terceraev.ui.Screens

import android.annotation.SuppressLint
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ex_terceraev.ui.Data.Producto
import com.example.ex_terceraev.ui.Navigation.Screens
import androidx.compose.material3.*
import com.example.ex_terceraev.ui.viewmodel.ProductoViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun presupuesto(viewmodel: ProductoViewModel) {
    val scrollState = rememberLazyListState()
    Column(
        modifier= Modifier.padding(top =50.dp)

    ) {
        Column {
            Text(text = "${viewmodel.total}")
        }


        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(bottom=50.dp),
            contentPadding = PaddingValues(bottom = 8.dp),
            state = scrollState
        ) {
           items(viewmodel.productos){
            producto   ->
               checkboxproductos(producto, viewmodel)

            }


        }
    }
}


@Composable
fun checkboxproductos(producto: Producto, viewModelproducto: ProductoViewModel) {

    var checkedstate by rememberSaveable { mutableStateOf(viewModelproducto.listaproductoseleccionado.contains(producto)) }
    val countproductos by rememberSaveable {
        mutableStateOf(viewModelproducto.getnumeroproductos(producto))
    }


    Card (modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
    shape = RoundedCornerShape(8.dp)){

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()){

            Checkbox(checked =checkedstate , onCheckedChange ={
                checkedstate = it

                if(checkedstate){
                    viewModelproducto.sumarproducto(producto)
                    viewModelproducto.añadircontador()
                }else{
                    viewModelproducto.restarproducto(producto)
                    viewModelproducto.restarcontador()

                }
            }



             )
            Column {
                Text(text = "${producto.nombre}")
                Text(text = "${producto.precio}€")
                Text(text = "$countproductos")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { viewModelproducto.restarcontador()
            viewModelproducto.decrementarContador(producto)
            viewModelproducto.borrarproductos(producto)}) {
                Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Black)
            }


            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { viewModelproducto.añadircontador()
            viewModelproducto.incrementarContador(producto)
            viewModelproducto.añadirproducto(producto)}) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.Black)
            }

        }


    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun scaffold(viewModelproducto: ProductoViewModel, navController: NavController){


    var scaffoldBackgroundColor by remember { mutableStateOf(Color.Blue) }


    Scaffold(
        topBar = {
            TopAppBar(
                colors= TopAppBarDefaults.mediumTopAppBarColors (containerColor= MaterialTheme.colorScheme.primary),
                title = { Text(text = "Presupuesto") },

                actions = {

                    Checkbox(
                        checked = viewModelproducto.checkall,
                        onCheckedChange = {
                            viewModelproducto.checkall = it
                            if(viewModelproducto.checkall){
                                viewModelproducto.seleccionartodosloscheck(viewModelproducto.productos)


                                 println(viewModelproducto.checkall)

                            }else{
                                viewModelproducto.cleartodosloscheck()

                                println(viewModelproducto.checkall)
                            }


                            navController.navigate(route= Screens.paginaprincipal.route)


                        }


                    )



                    IconButton(onClick = {viewModelproducto.total}){
                     //  navController.navigate(route= Screens.confirmacion.route)}) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = Color.Black)
                    }
                   // Text(text = "Items en el carrito ${viewModelproducto.}")
                }
            )
        }, contentColor = scaffoldBackgroundColor,

            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                    tonalElevation = 10.dp
                ) {
                    Text(
                        text = "Items ${viewModelproducto.numerodeproductos}  Items Seleccionados {${viewModelproducto.contador}}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
            )

    {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            presupuesto(viewModelproducto)
        }
    }

}



