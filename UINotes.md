# Stat Sheet ChatGPT Convo

1. Top Tasks during play
   - Most checked by players, in order (roughly):
        1. Health, Focus, and Investiture
        2. Skill scores
        3. Weapon damage
        4. Talents (this game's equivalent to a D&D Feat)
   - Most changed during a session, in order:
        1. Health, Focus, and Investiture
        2. Marks (currency)
        3. Status Conditions
   - Requires quick editing (not necessarily 1 tap, but easy access)
        1. Health, Focus, and Investiture
           - I currently have these near the bottom of the screen. When tapped, they pop either a number pad (health) or single value stepper for quick editing.
   - Stay visible:
        1. Health, Focus, and Investiture
        2. Defenses (Physical, Spirutal, Cognitive) + Deflect
        3. Equipped weapon
        4. Lower priority: Attribute scores, skill ranks

2. Fixed region:
   - Top: Defenses, Defelect, Conditions
   - Bottom: Expendable + Refreshable Resources (Health, Focus, and Investiture)

3. Reference only vs. edited
   - attributes - reference
   - skills - reference
   - expertises - reference
   - talents - reference
   - actions - reference
   - inventory - edited infrequently
   - goals - edited very infrequently
   - notes/journal - edited infrequently
   - radiant ideals / spren / surges - edited infrequently

4. I do not totally understand the difference. I want it to be a clear reference sheet, but eventually do want to introduce digital dice rolling and convenient tool tips for rules. I do not think it should match the printed sheet 1:1.

5. Phone / Tablet
   - Same product, different layouts. Tablet will have room to breathe and show more information cleanly at once.

6. Primary navigation model
   - Definitely not all one scroll
   - Maybe a tab bar? There will be character level up editing and rules references as well, but these aren't frequent enough to require space on the tab bar.
   - Some segmented control, but this may be better off with the tab bar.
   - Sheet + Overlays should be reserved for small rules explanations (ie tapping Disoriented will show a small tooltip with that conditions play information). Or tapping a skill will show how its value was calculated (attribute+rank+item boost+talent boost)
   - Absolutely not mode based navigation. You're far off on that one. The game is centered around a series of endeavors, and a social encounter, exploration sequence, or combat encounter can all be run similarly.

7. Actions are not the center of play. First and foremost this is a reference sheet for stat levels and a tracker for resources. I do not want "Raise the Stakes" surfaced prominantly. That can come later with digital dice rolling.

8. I have this solved in a way I am satisfied with. The resources live on the bottom of the screen. Tapping them pops a small stepper to allow for adjustment. For health it shows an alert with numeric keypad and field along with a heal button and a damage button.

9. Talent's should likely be grouped by path. Later we can add an option to customize that for the user. Skills should mainly be browsed or referenced in v1.

10. Narrative info is more like a notebook for a Player to occasionally jot in or reference. It does not need a prominent place in the app.

11. For now, it only needs to show stats. First from that list that I would add would be a way to inspect why a value is what it is. It should not overly worry itself with actions.

12. The current web app is usable but lacks visual distinction. Also it seems to be making a lot of server calls and can be frustratingly slow. On mobile there is a lot of scrolling and awkward navigation to get to difference sections. When scrolling, pertinent information like resources or defenses usually end up off screen.

## Questions Round 2
1. No
2. Total only
3. Default first.
4. 4 bottom tabs is probably ideal, but it could be 5. The last tab can be a menu for different sections like notes.

Note: Deflect is a passive stat gained from equipped armor. It belongs with Defenses (reduces incoming damage). It should also appear in the health adjuster as an optional toggle for the app to do the calculation automatically.

Conditions are lower priority. They are not rare, but I suspect a player may not want to track them actively in the app (slowed for a single round would be annoying).