package com.gannon.cosmere_kmp.rules.skill

import com.gannon.cosmere_kmp.util.EnumDisplayNamedOption
import com.gannon.cosmere_kmp.rules.attribute.Attribute
import com.gannon.cosmere_kmp.rules.attribute.AttributePair

//@Serializable
enum class Skill : EnumDisplayNamedOption {
    // Physical Skills
    Agility, Athletics, HeavyWeaponry, LightWeaponry, Stealth, Thievery,
    // Cognitive Skills
    Crafting, Deduction, Discipline, Intimidation, Lore, Medicine,
    // Spiritual Skills
    Deception, Insight, Leadership, Perception, Persuasion, Survival,
    // Surges
    Abrasion, Adhesion, Cohesion, Division, Gravitation, Illumination, Progression, Tension, Transformation, Transportation;

    /** Human-readable label for UI presentation. */
    override val displayName: String
        get() = when (this) {
            HeavyWeaponry -> "Heavy Weapons"
            LightWeaponry -> "Light Weaponry"
            else -> name.lowercase().replaceFirstChar { it.uppercase() }
        }

    /** Attribute that governs this skill. */
    val associatedAttribute: Attribute
        get() = when (this) {
            Athletics, HeavyWeaponry, Tension -> Attribute.Strength
            Agility, LightWeaponry, Stealth, Thievery, Abrasion -> Attribute.Speed
            Crafting, Deduction, Lore, Medicine, Division, Transportation -> Attribute.Intellect
            Discipline, Intimidation, Cohesion, Transformation -> Attribute.Willpower
            Insight, Perception, Survival, Gravitation, Progression -> Attribute.Awareness
            Deception, Leadership, Persuasion, Adhesion, Illumination -> Attribute.Presence
        }

    /** Whether this skill represents surge use. */
    val isSurge: Boolean
        get() = when (this) {
            Abrasion,
            Adhesion,
            Cohesion,
            Division,
            Gravitation,
            Illumination,
            Progression,
            Tension,
            Transformation,
            Transportation -> true
            else -> false
        }

    companion object {
        val physicalSkills: List<Skill> = skills(AttributePair.Physical)
        val cognitiveSkills: List<Skill> = skills(AttributePair.Cognitive)
        val spiritualSkills: List<Skill> = skills(AttributePair.Spiritual)

        val strengthSkills: List<Skill> = skills(Attribute.Strength)
        val speedSkills: List<Skill> = skills(Attribute.Speed)
        val intellectSkills: List<Skill> = skills(Attribute.Intellect)
        val willpowerSkills: List<Skill> = skills(Attribute.Willpower)
        val awarenessSkills: List<Skill> = skills(Attribute.Awareness)
        val presenceSkills: List<Skill> = skills(Attribute.Presence)

        fun skills(
            forAttributePair: AttributePair,
            includeSurges: Boolean = false
        ): List<Skill> = when (forAttributePair) {
            AttributePair.Physical -> filterSurges(physicalSkills, includeSurges)
            AttributePair.Cognitive -> filterSurges(cognitiveSkills, includeSurges)
            AttributePair.Spiritual -> filterSurges(spiritualSkills, includeSurges)
        }

        fun skills(
            forAttribute: Attribute,
            includeSurges: Boolean = false
        ): List<Skill> {
            val skills = when (forAttribute) {
                Attribute.Strength -> strengthSkills
                Attribute.Speed -> speedSkills
                Attribute.Intellect -> intellectSkills
                Attribute.Willpower -> willpowerSkills
                Attribute.Awareness -> awarenessSkills
                Attribute.Presence -> presenceSkills
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