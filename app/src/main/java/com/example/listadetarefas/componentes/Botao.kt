package com.example.listadetarefas.componentes

import android.widget.Button
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.listadetarefas.ui.theme.LIGHT_BLUE
import com.example.listadetarefas.ui.theme.WHITE

@Composable
fun Botao(
    onClick: () -> Unit,
    modifier: Modifier,
    texto:String
){
    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = WHITE,
            containerColor = LIGHT_BLUE
        )
    ) {
        Text(text = texto, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}