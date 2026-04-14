package com.gannon.cosmere_kmp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gannon.cosmere_kmp.rules.skill.SkillRankState

private object SkillRankLayout {
    val circleDiameter = 16.dp
    val modifierCornerRadius = 16.dp
}

interface SkillRankDisplayable {
    val skillName: String
    val attributeLabel: String
    val attributeScore: Int
    val ranks: Int
    val bonusRanks: Int?
}

val SkillRankDisplayable.flatSkillMod: Int
    get() = attributeScore + ranks

val SkillRankDisplayable.skillMod: Int
    get() = attributeScore + ranks + (bonusRanks ?: 0)

val SkillRankDisplayable.nameLabel: String
    get() = "$skillName($attributeLabel)"

@Composable
fun SkillRankView(
    skillInfo: SkillRankDisplayable,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(SkillRankLayout.modifierCornerRadius)
                )
                .defaultMinSize(minWidth = 56.dp)
                .padding(horizontal = 20.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = skillInfo.skillMod.toString(),
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = skillInfo.nameLabel,
            modifier = Modifier.padding(start = 12.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..5) {
                SkillRankDot(state = getSkillRankState(skillInfo, i))
            }
        }
    }
}

@Composable
private fun SkillRankDot(
    state: SkillRankState,
    modifier: Modifier = Modifier,
) {
    val baseModifier = modifier.size(SkillRankLayout.circleDiameter)

    when (state) {
        SkillRankState.Empty -> {
            Box(
                modifier = baseModifier.border(2.dp, Color.Black, CircleShape)
            )
        }

        SkillRankState.Claimed -> {
            Box(
                modifier = baseModifier.background(Color.Black, CircleShape)
            )
        }

        SkillRankState.Granted -> {
            Box(
                modifier = baseModifier.background(Color.Blue, CircleShape)
            )
        }

        SkillRankState.Ineligible -> {
            Box(
                modifier = baseModifier.background(Color.Gray, CircleShape)
            )
        }

        SkillRankState.Available -> {
            Box(
                modifier = baseModifier.border(2.dp, Color.Yellow, CircleShape)
            )
        }

        SkillRankState.Selected -> {
            Box(
                modifier = baseModifier.background(Color.Yellow, CircleShape)
            )
        }
    }
}

private fun getSkillRankState(
    skillInfo: SkillRankDisplayable,
    i: Int,
): SkillRankState {
    if (i <= skillInfo.ranks) return SkillRankState.Claimed
    if (i <= skillInfo.ranks + (skillInfo.bonusRanks ?: 0)) return SkillRankState.Granted
    // TODO: This is a placeholder value for actual skill rank max at Tier
    if (3 < i) return SkillRankState.Ineligible
    return SkillRankState.Empty
}
