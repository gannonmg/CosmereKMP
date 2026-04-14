package com.gannon.cosmere_kmp.rules.skill

import com.gannon.cosmere_kmp.rules.EnumDisplayNamedOption
import com.gannon.cosmere_kmp.rules.attribute.Attribute
import com.gannon.cosmere_kmp.rules.attribute.AttributePair

//@Serializable
enum class Skill : EnumDisplayNamedOption {
    AGILITY,
    ATHLETICS,
    HEAVY_WEAPONS,
    LIGHT_WEAPONS,
    STEALTH,
    THIEVERY,
    CRAFTING,
    DEDUCTION,
    DISCIPLINE,
    INTIMIDATION,
    LORE,
    MEDICINE,
    DECEPTION,
    INSIGHT,
    LEADERSHIP,
    PERCEPTION,
    PERSUASION,
    SURVIVAL,
    ABRASION,
    ADHESION,
    COHESION,
    DIVISION,
    GRAVITATION,
    ILLUMINATION,
    PROGRESSION,
    TENSION,
    TRANSFORMATION,
    TRANSPORTATION;

    /** Human-readable label for UI presentation. */
    override val displayName: String
        get() = when (this) {
            HEAVY_WEAPONS -> "Heavy Weapons"
            LIGHT_WEAPONS -> "Light Weapons"
            else -> name.lowercase().replaceFirstChar { it.uppercase() }
        }

    /** Attribute that governs this skill. */
    val associatedAttribute: Attribute
        get() = when (this) {
            ATHLETICS, HEAVY_WEAPONS, TENSION -> Attribute.STRENGTH
            AGILITY, LIGHT_WEAPONS, STEALTH, THIEVERY, ABRASION -> Attribute.SPEED
            CRAFTING, DEDUCTION, LORE, MEDICINE, DIVISION, TRANSPORTATION -> Attribute.INTELLECT
            DISCIPLINE, INTIMIDATION, COHESION, TRANSFORMATION -> Attribute.WILLPOWER
            DECEPTION, LEADERSHIP, PERCEPTION, SURVIVAL, GRAVITATION, PROGRESSION -> Attribute.AWARENESS
            INSIGHT, PERSUASION, ADHESION, ILLUMINATION -> Attribute.PRESENCE
        }

    /** Whether this skill represents surge use. */
    val isSurge: Boolean
        get() = when (this) {
            ABRASION,
            ADHESION,
            COHESION,
            DIVISION,
            GRAVITATION,
            ILLUMINATION,
            PROGRESSION,
            TENSION,
            TRANSFORMATION,
            TRANSPORTATION -> true
            else -> false
        }

    companion object {
        val physicalSkills: List<Skill> = skills(AttributePair.PHYSICAL)
        val cognitiveSkills: List<Skill> = skills(AttributePair.COGNITIVE)
        val spiritualSkills: List<Skill> = skills(AttributePair.SPIRITUAL)

        val strengthSkills: List<Skill> = skills(Attribute.STRENGTH)
        val speedSkills: List<Skill> = skills(Attribute.SPEED)
        val intellectSkills: List<Skill> = skills(Attribute.INTELLECT)
        val willpowerSkills: List<Skill> = skills(Attribute.WILLPOWER)
        val awarenessSkills: List<Skill> = skills(Attribute.AWARENESS)
        val presenceSkills: List<Skill> = skills(Attribute.PRESENCE)

        fun skills(
            forAttributePair: AttributePair,
            includeSurges: Boolean = false
        ): List<Skill> = when (forAttributePair) {
            AttributePair.PHYSICAL -> filterSurges(physicalSkills, includeSurges)
            AttributePair.COGNITIVE -> filterSurges(cognitiveSkills, includeSurges)
            AttributePair.SPIRITUAL -> filterSurges(spiritualSkills, includeSurges)
        }

        fun skills(
            forAttribute: Attribute,
            includeSurges: Boolean = false
        ): List<Skill> {
            val skills = when (forAttribute) {
                Attribute.STRENGTH -> strengthSkills
                Attribute.SPEED -> speedSkills
                Attribute.INTELLECT -> intellectSkills
                Attribute.WILLPOWER -> willpowerSkills
                Attribute.AWARENESS -> awarenessSkills
                Attribute.PRESENCE -> presenceSkills
            }

            return filterSurges(skills, includeSurges)
        }

        /** Cached grouping for an individual attribute. */
        private fun skills(attribute: Attribute): List<Skill> =
            entries.filter { it.associatedAttribute == attribute }

        /** Cached grouping for both attributes in a pair. */
        private fun skills(attributePair: AttributePair): List<Skill> =
            (skills(attributePair.first) + skills(attributePair.second))
                .sortedBy { it.name }

        private fun filterSurges(skills: List<Skill>, includeSurges: Boolean): List<Skill> =
            if (includeSurges) skills else skills.filterNot { it.isSurge }
    }
}
