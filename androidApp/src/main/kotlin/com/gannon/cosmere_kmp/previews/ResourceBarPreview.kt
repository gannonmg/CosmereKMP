package com.gannon.cosmere_kmp.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gannon.cosmere_kmp.character.sheet.ResourcePool
import com.gannon.cosmere_kmp.ui.FloatingBottomBarLayout
import com.gannon.cosmere_kmp.ui.ResourceBarEntry
import com.gannon.cosmere_kmp.ui.ResourceInteraction
import com.gannon.cosmere_kmp.ui.ResourceBarView

@Preview(
    showBackground = true,
    backgroundColor = 0xFFF6F1E7,
    heightDp = 800,
)
@Composable
fun ResourceBarPreview() {
    val resources = listOf(
        ResourceBarEntry(
            resource = ResourcePool.Health,
            current = 19,
            maximum = 36,
            interaction = ResourceInteraction.HealthDialog(
                onApplyDamage = {},
                onApplyHeal = {},
            ),
        ),
        ResourceBarEntry(
            resource = ResourcePool.Focus,
            current = 4,
            maximum = 6,
            interaction = ResourceInteraction.Stepper(
                onDecrement = {},
                onIncrement = {},
            ),
        ),
        ResourceBarEntry(
            resource = ResourcePool.Investiture,
            current = 2,
            maximum = 5,
            interaction = ResourceInteraction.Stepper(
                onDecrement = {},
                onIncrement = {},
            ),
        ),
    )

    FloatingBottomBarLayout(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp)
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                repeat(12) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black.copy(alpha = 0.12f), RoundedCornerShape(20.dp))
                            .background(Color.White, RoundedCornerShape(20.dp))
                    ) {
                        BasicText(
                            text = "Scroll content row ${index + 1}",
                            modifier = Modifier.padding(20.dp),
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )
            }
        },
        bottomBar = {
            ResourceBarView(resources = resources)
        },
    )
}
