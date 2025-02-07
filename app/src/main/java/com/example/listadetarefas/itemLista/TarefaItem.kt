package com.example.listadetarefas.itemLista

import androidx.compose.foundation.Image
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.listadetarefas.R
import com.example.listadetarefas.model.Tarefa
import com.example.listadetarefas.ui.theme.BLACK
import com.example.listadetarefas.ui.theme.GREEN_SELECTED
import com.example.listadetarefas.ui.theme.RED_SELECTED
import com.example.listadetarefas.ui.theme.ShapeCardPrioridade
import com.example.listadetarefas.ui.theme.YELLOW_SELECTED

@Composable
fun TarefaItem(
    position: Int,
    listaTarefas: MutableList<Tarefa>
) {
    //recupera os dados de cada tarefa
    val tituloTarefa = listaTarefas [position].tarefa
    val descricaoTarefa = listaTarefas [position].descricao
    val prioridade = listaTarefas [position].prioridade

    var nivelPrioridade: String = when(prioridade){

        0->{
            "Sem prioridade"
        }
        1->{
            "Prioridade Baixa"
        }
        2->{
            "Prioridade Média"
        }
        else ->{
            "Prioridade Alta"
        }
    }

    val color = when(prioridade){
        0->{
            BLACK
        }
        1->{
            GREEN_SELECTED
        }
        2->{
            YELLOW_SELECTED
        }
        else->{
            RED_SELECTED
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {
            //criando as referencias dos componentes
            val (txtTitulo, txtDescricao, cardPrioridade, txtPrioridade, btnDeletar) = createRefs()

            Text(text = tituloTarefa.toString(),
                modifier = Modifier.constrainAs(txtTitulo) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)

            })

            Text(text = descricaoTarefa.toString(),
                modifier = Modifier.constrainAs(txtDescricao) {
                    top.linkTo(txtTitulo.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
            })

            Text(text = nivelPrioridade,
                modifier = Modifier.constrainAs(txtPrioridade) {
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
            })

            Card(
                colors = CardDefaults.cardColors(color),
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(cardPrioridade) {
                        top.linkTo(txtDescricao.bottom, margin = 10.dp)
                        start.linkTo(txtPrioridade.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    },
                shape = ShapeCardPrioridade.large

            ) {

            }

            IconButton(
                onClick = {

                },
                modifier = Modifier.constrainAs(btnDeletar) {
                    top.linkTo(parent.top, margin = 80.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    start.linkTo(cardPrioridade.end, margin = 80.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            ) {
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_deletar), contentDescription = null)
            }
        }
    }
}