package view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listadetarefas.componentes.Botao
import com.example.listadetarefas.componentes.CaixaDeTexto
import com.example.listadetarefas.constantes.Constantes
import com.example.listadetarefas.repositorio.TarefasRepositorio
import com.example.listadetarefas.ui.theme.GREEN_DISABLED
import com.example.listadetarefas.ui.theme.GREEN_SELECTED
import com.example.listadetarefas.ui.theme.PurpleGrey40
import com.example.listadetarefas.ui.theme.RED_DISABLED
import com.example.listadetarefas.ui.theme.RED_SELECTED
import com.example.listadetarefas.ui.theme.YELLOW_DISABLED
import com.example.listadetarefas.ui.theme.YELLOW_SELECTED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarTarefa(navController: NavController) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tarefasRepositorio = TarefasRepositorio()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = PurpleGrey40),
                title = {
                    Text(
                        text = "Salvar Tarefa",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                }
            )
        }
    ) { paddingValues ->
        //Ele é frequentemente utilizado em composables que necessitam de ajustes de
        // espaçamento (margens internas) dentro de contêineres, como no caso de Scaffold, Column, Row, Box

        var tituloTarefa by remember {
            mutableStateOf("")
        }

        var descricaoTarefa by remember {
            mutableStateOf("")
        }

        var semPrioridadeTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeBaixaTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeMédiaTarefa by remember {
            mutableStateOf(false)
        }

        var urgenteTarefa by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {

            CaixaDeTexto(
                value = tituloTarefa,
                onValueChange = {
                    tituloTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                label = "Titulo Tarefa",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )

            CaixaDeTexto(
                value = descricaoTarefa,
                onValueChange = {
                    descricaoTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 150.dp)
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                label = "Descrição",
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Nível de prioridade")
                RadioButton(
                    selected = prioridadeBaixaTarefa,
                    onClick = {
                        prioridadeBaixaTarefa = !prioridadeBaixaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = GREEN_DISABLED,
                        selectedColor = GREEN_SELECTED
                    )
                )

                RadioButton(
                    selected = prioridadeMédiaTarefa,
                    onClick = {
                        prioridadeMédiaTarefa = !prioridadeMédiaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = YELLOW_DISABLED,
                        selectedColor = YELLOW_SELECTED
                    )
                )

                RadioButton(
                    selected = urgenteTarefa,
                    onClick = {
                        urgenteTarefa = !urgenteTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RED_DISABLED,
                        selectedColor = RED_SELECTED
                    )
                )
            }

            Botao(
                onClick = {
                    //thread paralela
                    var mensagem = true

                    scope.launch(Dispatchers.IO) {
                        if (tituloTarefa.isEmpty()) {
                            mensagem = false
                        } else if (tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeBaixaTarefa) {
                            tarefasRepositorio.salvarTarefa(
                                tituloTarefa,
                                descricaoTarefa,
                                Constantes.PRIORIDADE_BAIXA
                            )
                            mensagem = true
                        } else if (tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && prioridadeMédiaTarefa) {
                            tarefasRepositorio.salvarTarefa(
                                tituloTarefa,
                                descricaoTarefa,
                                Constantes.PRIORIDADE_MEDIA
                            )
                            mensagem = true
                        } else if (tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && urgenteTarefa) {
                            tarefasRepositorio.salvarTarefa(
                                tituloTarefa,
                                descricaoTarefa,
                                Constantes.PRIORIDADE_ALTA
                            )
                            mensagem = true
                        } else if (tituloTarefa.isNotEmpty() && descricaoTarefa.isNotEmpty() && semPrioridadeTarefa) {
                            tarefasRepositorio.salvarTarefa(
                                tituloTarefa,
                                descricaoTarefa,
                                Constantes.SEM_PRIORIDADE
                            )
                            mensagem = true
                        } else if (tituloTarefa.isNotEmpty() && prioridadeBaixaTarefa) {
                            tarefasRepositorio.salvarTarefa(
                                tituloTarefa,
                                descricaoTarefa,
                                Constantes.PRIORIDADE_BAIXA
                            )
                            mensagem = true
                        } else if (tituloTarefa.isNotEmpty() && prioridadeMédiaTarefa) {
                            tarefasRepositorio.salvarTarefa(
                                tituloTarefa,
                                descricaoTarefa,
                                Constantes.PRIORIDADE_MEDIA
                            )
                            mensagem = true
                        } else if (tituloTarefa.isNotEmpty() && urgenteTarefa) {
                            tarefasRepositorio.salvarTarefa(
                                tituloTarefa,
                                descricaoTarefa,
                                Constantes.PRIORIDADE_ALTA
                            )
                            mensagem = true
                        } else {
                            tarefasRepositorio.salvarTarefa(
                                tituloTarefa,
                                descricaoTarefa,
                                Constantes.SEM_PRIORIDADE
                            )
                            mensagem = true
                        }
                    }

                    scope.launch(Dispatchers.Main) {

                        if (mensagem) {
                            Toast.makeText(
                                context,
                                "Sucesso ao salvar a tarefa!",
                                Toast.LENGTH_SHORT
                            ).show()

                            //voltar automaticamente para a tela anterior
                            navController.popBackStack()

                        } else {
                            Toast.makeText(
                                context,
                                "Título da tarefa é obrigatório",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(20.dp),
                texto = "Salvar"
            )
        }
    }
}
