package com.hkr.health.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Heading(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        modifier = Modifier
            .padding(10.dp)
    )
}