package com.gannon.cosmere_kmp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gannon.cosmere_kmp.character.engine.CharacterBuild
import com.gannon.cosmere_kmp.rules.attribute.AttributePair
import com.gannon.cosmere_kmp.rules.skill.Skill

enum class StatSectionDisplayMode {
    Stacked, Paged,
}

interface UiPreferences {
    fun getStatSectionDisplayMode(): StatSectionDisplayMode
    fun setStatSectionDisplayMode(mode: StatSectionDisplayMode)
}

@Composable
fun StatSectionView(
    build: CharacterBuild,
    displayMode: StatSectionDisplayMode,
    onDisplayModeChange: (StatSectionDisplayMode) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        StatSectionDisplayModeToggle(
            displayMode = displayMode,
            onDisplayModeChange = onDisplayModeChange
        )
        val attributePairs = AttributePair.entries
        when (displayMode) {
            StatSectionDisplayMode.Stacked -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    attributePairs.forEach { attributePair ->
                        StatSection(build, attributePair, Modifier.fillMaxWidth())
                    }
                }
            }

            StatSectionDisplayMode.Paged -> {
                val pagerState = rememberPagerState(
                    pageCount = { attributePairs.size }
                )

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) { page ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        StatSection(
                            build = build,
                            attributePair = attributePairs[page],
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatSectionDisplayModeToggle(
    displayMode: StatSectionDisplayMode,
    onDisplayModeChange: (StatSectionDisplayMode) -> Unit,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(
            onClick = { onDisplayModeChange(StatSectionDisplayMode.Stacked) },
            colors = if (displayMode == StatSectionDisplayMode.Stacked) {
                ButtonDefaults.buttonColors()
            } else {
                ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                )
            }
        ) {
            Text("Stacked")
        }

        Button(
            onClick = { onDisplayModeChange(StatSectionDisplayMode.Paged) },
            colors = if (displayMode == StatSectionDisplayMode.Paged) {
                ButtonDefaults.buttonColors()
            } else {
                ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                )
            }
        ) {
            Text("Paged")
        }
    }
}


@Composable
fun StatSection(
    build: CharacterBuild,
    attributePair: AttributePair,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val firstVal = build.attributes[attributePair.first] ?: 0
        val secondVal = build.attributes[attributePair.second] ?: 0
        val defense = 10 + firstVal + secondVal
        AttributePairView(
            title = attributePair.displayName,
            firstTitle = attributePair.first.displayName,
            secondTitle = attributePair.second.displayName,
            firstValue = firstVal,
            secondValue = secondVal,
            defense = defense,
        )
        Skill.skills(forAttributePair = attributePair).forEach { skill ->
            val rank = build.skillRanks[skill] ?: 0
            val attributeScore = build.attributes[skill.associatedAttribute] ?: 0
            SkillRankView(
                skillName = skill.displayName,
                attributeLabel = skill.associatedAttribute.shortLabel,
                attributeScore = attributeScore,
                ranks = rank,
            )
        }
    }
}
