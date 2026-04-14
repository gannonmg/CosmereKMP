package com.gannon.cosmere_kmp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gannon.cosmere_kmp.ui.AttributePairDisplayable
import com.gannon.cosmere_kmp.ui.AttributePairView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

private data class PreviewAttributePair(
    override val title: String,
    override val firstTitle: String,
    override val secondTitle: String,
    override val firstValue: Int,
    override val secondValue: Int,
    override val defense: Int,
) : AttributePairDisplayable

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
            displayable = PreviewAttributePair(
                title = "Physical",
                firstTitle = "Strength",
                secondTitle = "Speed",
                firstValue = 1,
                secondValue = 3,
                defense = 14
            )
        )
    }
}
