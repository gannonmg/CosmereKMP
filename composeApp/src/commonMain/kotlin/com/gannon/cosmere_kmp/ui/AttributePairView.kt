package com.gannon.cosmere_kmp.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gannon.cosmere_kmp.character.build.AttributePairDisplayable

@Composable
fun AttributePairView(
    title: String,
    firstTitle: String,
    secondTitle: String,
    firstValue: Int,
    secondValue: Int,
    defense: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AttributeValueBox(
                title = firstTitle,
                value = firstValue
            )

            DefenseBox(defense = defense)

            AttributeValueBox(
                title = secondTitle,
                value = secondValue
            )
        }
    }
}

@Composable
private fun AttributeValueBox(
    title: String,
    value: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .border(1.dp, Color.Black)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value.toString(),
            fontSize = 40.sp
        )
    }
}

@Composable
private fun DefenseBox(
    defense: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Defense",
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier
                .border(1.dp, Color.Black)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = defense.toString(),
                fontSize = 32.sp
            )
        }
    }
}