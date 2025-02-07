package com.example.listadetarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listadetarefas.ui.theme.ListaDeTarefasTheme
import view.ListaTarefas
import view.SalvarTarefa

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaDeTarefasTheme {

                //A variável vai herdar de um navHostController (possibilitando a nvageção de telas)
                val navController = rememberNavController()

                //Configurando rotas (ID e telas)
                NavHost(navController = navController, startDestination = "listaTarefas") {
                    composable(
                        route = "listaTarefas"
                    ){
                        ListaTarefas(navController)
                    }
                    composable(
                        route = "salvarTarefas"
                    ){
                        SalvarTarefa(navController)
                    }
                }
            }
        }
    }
}

