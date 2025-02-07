package com.example.listadetarefas.repositorio

import com.example.listadetarefas.datasource.DataSource
import com.example.listadetarefas.model.Tarefa
import kotlinx.coroutines.flow.Flow

class TarefasRepositorio{

    private val dataSource = DataSource()

    fun salvarTarefa(
        tarefa:String, descricao:String, prioridade:Int
    ){
        dataSource.salvarTarefa(tarefa, descricao, prioridade)

    }

    fun recuperarTarefas():Flow<MutableList<Tarefa>>{
        return dataSource.recuperarTarefas()
    }

}