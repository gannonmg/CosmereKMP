package com.gannon.cosmere_kmp.previews

import com.gannon.cosmere_kmp.ui.AttributePairView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
fun AttributePairPreview() {
    Box(
        modifier = Modifier
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        AttributePairView(
            title = "Physical",
            firstTitle = "Strength",
            secondTitle = "Speed",
            firstValue = 1,
            secondValue = 3,
            defense = 14
        )
    }
}
