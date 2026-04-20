package com.gannon.cosmere_kmp.character.engine

import com.gannon.cosmere_kmp.character.build.IdentifiedLevelUpChoice
import com.gannon.cosmere_kmp.character.build.LevelUpChoice
import com.gannon.cosmere_kmp.rules.attribute.Attribute
import com.gannon.cosmere_kmp.rules.skill.Skill
import com.gannon.cosmere_kmp.rules.stormlight.StormlightAncestry

class CharacterEngine {
    companion object {
        fun deriveCharacterBuild(
            levelUps: Map<Int, Array<IdentifiedLevelUpChoice>>,
            atLevel: Int,
        ): CharacterBuild {
            var ancestry: StormlightAncestry? = null
            val attributes = mutableMapOf<Attribute, Int>()
            val skillRanks = mutableMapOf<Skill, Int>()

            for (level in 1..atLevel) {
                val choices = levelUps[level] ?: continue
                choices.forEach { idChoice ->
                    when (val choice = idChoice.choice) {
                        is LevelUpChoice.ChooseAncestry -> { ancestry = choice.ancestry }
                        is LevelUpChoice.IncreaseAttribute -> {
                            attributes[choice.attribute] = (attributes[choice.attribute] ?: 0) + choice.amount
                        }
                        is LevelUpChoice.IncreaseSkillRank -> {
                            skillRanks[choice.skill] = (skillRanks[choice.skill] ?: 0) + choice.amount
                        }
                    }
                }
            }

            return CharacterBuild(
                ancestry = ancestry,
                attributes = attributes,
                skillRanks = skillRanks
            )
        }
    }
}

data class CharacterBuild(
    val ancestry: StormlightAncestry?,
    val attributes: Map<Attribute, Int>,
    val skillRanks: Map<Skill, Int>
)