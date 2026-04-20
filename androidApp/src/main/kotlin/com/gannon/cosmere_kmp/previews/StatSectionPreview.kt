package com.gannon.cosmere_kmp.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gannon.cosmere_kmp.character.build.IdentifiedLevelUpChoice
import com.gannon.cosmere_kmp.ui.StatSectionView
import com.gannon.cosmere_kmp.character.engine.CharacterEngine
import com.gannon.cosmere_kmp.rules.attribute.Attribute
import com.gannon.cosmere_kmp.rules.skill.Skill
import com.gannon.cosmere_kmp.ui.StatSectionDisplayMode
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.gannon.cosmere_kmp.rules.stormlight.StormlightAncestry


@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
fun StatSectionPreview() {
    var displayMode by remember { mutableStateOf(StatSectionDisplayMode.Stacked) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        StatSectionView(
            build = CharacterEngine.deriveCharacterBuild(
                levelUps = mapOf(
                    1 to arrayOf(
                        IdentifiedLevelUpChoice.chooseAncestry(
                            ancestry = StormlightAncestry.Human
                        ),
                        IdentifiedLevelUpChoice.increaseAttribute(
                            attribute = Attribute.Strength,
                            amount = 1
                        ),
                        IdentifiedLevelUpChoice.increaseAttribute(
                            attribute = Attribute.Speed,
                            amount = 3
                        ),
                        IdentifiedLevelUpChoice.increaseAttribute(
                            attribute = Attribute.Intellect,
                            amount = 3
                        ),
                        IdentifiedLevelUpChoice.increaseAttribute(
                            attribute = Attribute.Willpower,
                            amount = 2
                        ),
                        IdentifiedLevelUpChoice.increaseAttribute(
                            attribute = Attribute.Presence,
                            amount = 2
                        ),
                        IdentifiedLevelUpChoice.increaseAttribute(
                            attribute = Attribute.Awareness,
                            amount = 2
                        ),
                        IdentifiedLevelUpChoice.increaseSkillRank(
                            skill = Skill.Agility
                        ),
                        IdentifiedLevelUpChoice.increaseSkillRank(
                            skill = Skill.Stealth,
                        ),
                        IdentifiedLevelUpChoice.increaseSkillRank(
                            skill = Skill.Lore,
                            amount = 2
                        ),
                        IdentifiedLevelUpChoice.increaseSkillRank(
                            skill = Skill.Crafting,
                            amount = 2
                        ),
                    )
                ),
                atLevel = 1
            ),
            displayMode = displayMode,
            onDisplayModeChange = { displayMode = it }
        )
    }
}
