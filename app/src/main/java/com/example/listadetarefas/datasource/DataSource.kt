package com.example.listadetarefas.datasource

import android.annotation.SuppressLint
import com.example.listadetarefas.model.Tarefa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource {

    private val db = FirebaseFirestore.getInstance()

    //estado de fluxo onde recebe a mutable list de tarefas
    private val _todastarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())

    //variavel que observa o fluxo de dados retornado de todas as tarefas
    private val todasTarefas:StateFlow<MutableList<Tarefa>> = _todastarefas

    @SuppressLint("SuspiciousIndentation")
    fun salvarTarefa(tarefa:String, descricao:String, prioridade:Int){


        //criando as tabelas no banco de dados
        val tarefaMap = hashMapOf(
            "tarefa" to tarefa,
            "descricao" to descricao,
            "prioridade" to prioridade
        )
            db.collection("tarefas").document(tarefa).set(tarefaMap).addOnCompleteListener{

            }.addOnFailureListener{

            }
    }

            //retorna um FLOW, fluxo de dados assíncrono do coroutine
            fun recuperarTarefas(): Flow<MutableList<Tarefa>> {
                val listaTarefa: MutableList<Tarefa> = mutableListOf()
                db.collection("tarefas").get().addOnCompleteListener { querySnapshot ->
                    if (querySnapshot.isSuccessful) {
                        for (documento in querySnapshot.result) {
                            val tarefa = documento.toObject(Tarefa::class.java)
                            listaTarefa.add(tarefa)
                        }
                        _todastarefas.value = listaTarefa  // <- Atualiza depois do loop
                    }
                }
                return todasTarefas
            }

}