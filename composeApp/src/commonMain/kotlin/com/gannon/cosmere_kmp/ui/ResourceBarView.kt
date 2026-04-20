package com.gannon.cosmere_kmp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gannon.cosmere_kmp.character.sheet.ResourcePool

private object ResourceBarLayout {
    val horizontalPadding = 16.dp
    val bottomSpacing = 10.dp
    val outerCornerRadius = 24.dp
    val itemCornerRadius = 18.dp
    val itemSpacing = 8.dp
    val cardPadding = 10.dp
    val actionButtonCornerRadius = 999.dp
    val keypadSpacing = 8.dp
    val keypadButtonSize = 52.dp
}

data class ResourceBarEntry(
    val resource: ResourcePool,
    val current: Int,
    val maximum: Int,
    val interaction: ResourceInteraction,
)

sealed interface ResourceInteraction {
    data class Stepper(
        val onDecrement: (() -> Unit)? = null,
        val onIncrement: (() -> Unit)? = null,
    ) : ResourceInteraction

    data class HealthDialog(
        val onApplyDamage: (Int) -> Unit,
        val onApplyHeal: (Int) -> Unit,
    ) : ResourceInteraction
}

@Composable
fun FloatingBottomBarLayout(
    modifier: Modifier = Modifier,
    contentSpacing: Dp = ResourceBarLayout.bottomSpacing,
    content: @Composable (PaddingValues) -> Unit,
    bottomBar: @Composable BoxScope.() -> Unit,
) {
    var bottomBarHeightPx by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val bottomPadding = with(density) { bottomBarHeightPx.toDp() } + contentSpacing

    Box(modifier = modifier.fillMaxSize()) {
        content(PaddingValues(bottom = bottomPadding))

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .onSizeChanged { bottomBarHeightPx = it.height },
            content = bottomBar,
        )
    }
}

@Composable
fun ResourceBarView(
    resources: List<ResourceBarEntry>,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = ResourceBarLayout.horizontalPadding)
            .padding(bottom = ResourceBarLayout.bottomSpacing)
            .navigationBarsPadding(),
        shape = RoundedCornerShape(ResourceBarLayout.outerCornerRadius),
        tonalElevation = 8.dp,
        shadowElevation = 12.dp,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ResourceBarLayout.cardPadding),
            horizontalArrangement = Arrangement.spacedBy(ResourceBarLayout.itemSpacing),
        ) {
            resources.forEach { resource ->
                ResourceTrackerCard(
                    entry = resource,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun ResourceTrackerCard(
    entry: ResourceBarEntry,
    modifier: Modifier = Modifier,
) {
    var showHealthSheet by remember(entry.resource) { mutableStateOf(false) }

    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.Black.copy(alpha = 0.12f),
                shape = RoundedCornerShape(ResourceBarLayout.itemCornerRadius),
            )
            .background(
                color = entry.resource.accentColor,
                shape = RoundedCornerShape(ResourceBarLayout.itemCornerRadius),
            )
            .padding(ResourceBarLayout.cardPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = entry.resource.label,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = entry.current.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = entry.maximum.toString(),
                fontSize = 12.sp,
                color = Color.Black.copy(alpha = 0.6f),
            )
        }

        when (val interaction = entry.interaction) {
            is ResourceInteraction.HealthDialog -> {
                ResourceActionButton(
                    label = "Adjust",
                    enabled = true,
                    onClick = { showHealthSheet = true },
                )

                if (showHealthSheet) {
                    HealthAdjustSheet(
                        current = entry.current,
                        maximum = entry.maximum,
                        onDismiss = { showHealthSheet = false },
                        onApplyDamage = { amount ->
                            interaction.onApplyDamage(amount)
                            showHealthSheet = false
                        },
                        onApplyHeal = { amount ->
                            interaction.onApplyHeal(amount)
                            showHealthSheet = false
                        },
                    )
                }
            }

            is ResourceInteraction.Stepper -> Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ResourceActionButton(
                    label = "-",
                    enabled = interaction.onDecrement != null,
                    onClick = interaction.onDecrement,
                )
                ResourceActionButton(
                    label = "+",
                    enabled = interaction.onIncrement != null,
                    onClick = interaction.onIncrement,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HealthAdjustSheet(
    current: Int,
    maximum: Int,
    onDismiss: () -> Unit,
    onApplyDamage: (Int) -> Unit,
    onApplyHeal: (Int) -> Unit,
) {
    var enteredAmount by remember { mutableStateOf("") }
    val amount = enteredAmount.toIntOrNull() ?: 0
    val canDamage = amount in 1..current
    val canHeal = amount in 1..(maximum - current)
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // This bottom sheet keeps health entry in thumb reach without blocking the center of the screen.
            Text(
                text = "Adjust Health",
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "$current / $maximum",
                color = Color.Black.copy(alpha = 0.6f),
            )
            Text(
                text = if (enteredAmount.isEmpty()) "0" else enteredAmount,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ResourceActionButton(
                    label = "Damage",
                    enabled = canDamage,
                    onClick = { onApplyDamage(amount) },
                )
                ResourceActionButton(
                    label = "Heal",
                    enabled = canHeal,
                    onClick = { onApplyHeal(amount) },
                )
            }
            NumericKeypad(
                onDigit = { digit ->
                    enteredAmount = (enteredAmount + digit).take(3).trimStart('0')
                },
                onBackspace = {
                    enteredAmount = enteredAmount.dropLast(1)
                },
                onClear = {
                    enteredAmount = ""
                },
            )
            ResourceActionButton(
                label = "Close",
                enabled = true,
                onClick = onDismiss,
            )
        }
    }
}

@Composable
private fun NumericKeypad(
    onDigit: (String) -> Unit,
    onBackspace: () -> Unit,
    onClear: () -> Unit,
) {
    val rows = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("C", "0", "<"),
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(ResourceBarLayout.keypadSpacing),
    ) {
        rows.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(ResourceBarLayout.keypadSpacing),
            ) {
                row.forEach { key ->
                    ResourceActionButton(
                        label = key,
                        enabled = true,
                        onClick = {
                            when (key) {
                                "C" -> onClear()
                                "<" -> onBackspace()
                                else -> onDigit(key)
                            }
                        },
                        modifier = Modifier.size(ResourceBarLayout.keypadButtonSize),
                    )
                }
            }
        }
    }
}

@Composable
private fun ResourceActionButton(
    label: String,
    enabled: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        onClick = { onClick?.invoke() },
        enabled = enabled,
        shape = RoundedCornerShape(ResourceBarLayout.actionButtonCornerRadius),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        shadowElevation = 0.dp,
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}
