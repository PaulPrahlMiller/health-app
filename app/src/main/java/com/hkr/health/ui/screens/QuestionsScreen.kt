package com.hkr.health.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Questions(
    onQuestionClick: (String) -> Unit,
    categories: List<String>
){
    LazyColumn() {
        items(categories){ category ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .clickable(
                        enabled = true,
                        onClick = { onQuestionClick(category) }
                    ),
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp
            ) {
                CardContent(category)
            }
        }
    }
}

@Composable
fun CardContent(category: String) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 20.dp)
                .clickable {

                }
        )
        Text(
            text = category.replaceFirstChar(Char::uppercase),
            modifier = Modifier
                .padding(
                    vertical = 10.dp,
                )
                .padding(start = 20.dp),
            fontSize = 30.sp
        )
    }
}