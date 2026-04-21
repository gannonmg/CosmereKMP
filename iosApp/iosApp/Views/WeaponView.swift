//
//  WeaponView.swift
//  iosApp
//
//  Created by Matt Gannon on 4/20/26.
//

import Shared
import SwiftUI

struct WeaponView: View {
    let weapon: Weapon
    let skillModifier: Int
    let isExpert: Bool

    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text(weapon.name)
                    .expertiseIndicator()
                    .fixedSize(horizontal: true, vertical: false)
                    .frame(maxWidth: .infinity, alignment: .leading)
                let skillDisplayString = if skillModifier > 0 {
                    "\(weapon.skill.displayName), +\(skillModifier)"
                } else {
                    weapon.skill.displayName
                }
                Text(skillDisplayString)
                    .font(.caption2)
                    .italic()
                    .fixedSize(horizontal: true, vertical: false)
            }

            VStack(alignment: .trailing) {
                let normalTraitString = weapon.traits.map(\.displayLabel).joined(separator: ", ")
                Text(normalTraitString)
                    .fixedSize(horizontal: true, vertical: false)
                let expertTraitString = weapon.expertTraits.map(\.displayLabel).joined(separator: ", ")
                Text(expertTraitString)
                    .expertiseIndicator(alignment: .leading)
                    .fixedSize(horizontal: true, vertical: false)
            }
            .font(.caption)
            .italic()

            Text(weapon.damageLabel(modifier: skillModifier).lowercased())
                .multilineTextAlignment(.center)
                .font(.footnote)
                .rollable(isCompact: true)
        }
        .padding()
        .background {
            RoundedRectangle(cornerRadius: 16)
                .foregroundStyle(.white)
                .shadow(radius: 2, x: 1, y: 2)
        }
    }
}

extension Weapon {
    func damageLabel(modifier: Int) -> String {
        var str = ""
        damageDice.forEach { dice in
            dice.diceRoll.forEach { roll in
                str.append("\(roll.amount)\(roll.dice.name)")
            }
            str.append("+\(modifier)\n\(dice.damageType.name)")
        }
        return str
    }
}

#Preview {
    WeaponView(
        weapon: .companion.dagger,
        skillModifier: 5,
        isExpert: true
    )
}
