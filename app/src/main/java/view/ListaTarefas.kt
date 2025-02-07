package view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listadetarefas.R
import com.example.listadetarefas.itemLista.TarefaItem
import com.example.listadetarefas.model.Tarefa
import com.example.listadetarefas.repositorio.TarefasRepositorio
import com.example.listadetarefas.ui.theme.BLACK
import com.example.listadetarefas.ui.theme.PurpleGrey40
import com.example.listadetarefas.ui.theme.WHITE

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
//Criação da TOPBAR
@Composable
fun ListaTarefas(navController: NavController) {

    val tarefasRepositorio = TarefasRepositorio()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = PurpleGrey40),
                title = {
                    Text(
                        text = "Lista de Tarefas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = WHITE
                    )
                }
            )
        },
        containerColor = BLACK,
        floatingActionButton = {
            //Implementação do FloatActionButton com seu click navegando entre telas
            FloatingActionButton(
                onClick = {
                    navController.navigate("salvarTarefas")
                },
                containerColor = PurpleGrey40

            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = "ícone de Salvar Tarefa"
                )
            }
        }
    ) { paddingValues ->

        val listaTarefas =
            tarefasRepositorio.recuperarTarefas().collectAsState(mutableListOf()).value
        //lista na vertical
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            itemsIndexed(listaTarefas) { position, _ ->
                TarefaItem(position = position, listaTarefas = listaTarefas)
            }

        }
    }
}
