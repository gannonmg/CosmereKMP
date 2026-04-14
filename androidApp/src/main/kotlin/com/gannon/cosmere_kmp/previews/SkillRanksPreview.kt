package com.gannon.cosmere_kmp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gannon.cosmere_kmp.ui.SkillRankDisplayable
import com.gannon.cosmere_kmp.ui.SkillRankView

private data class PreviewSkillRank(
    override val skillName: String,
    override val attributeLabel: String,
    override val attributeScore: Int,
    override val ranks: Int,
    override val bonusRanks: Int?,
) : SkillRankDisplayable

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
fun SkillRankPreview() {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SkillRankView(
            skillInfo = PreviewSkillRank(
                skillName = "Deduction",
                attributeLabel = "INT",
                attributeScore = 2,
                ranks = 3,
                bonusRanks = 1
            )
        )

        SkillRankView(
            skillInfo = PreviewSkillRank(
                skillName = "Intimidation",
                attributeLabel = "WIL",
                attributeScore = 1,
                ranks = 1,
                bonusRanks = 0
            )
        )
    }
}
