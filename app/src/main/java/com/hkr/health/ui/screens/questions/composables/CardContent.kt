package com.hkr.health.ui.screens.questions.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardContent(category: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 20.dp)
        )
        Text(
            text = category.replaceFirstChar(Char::uppercase),
            modifier = Modifier
                .padding(vertical = 10.dp)
                .padding(start = 20.dp),
            fontSize = 24.sp
        )
    }
}