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
    let isExpert: Bool

    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                HStack(spacing: 4) {
                    Text(weapon.name)
                    Image(systemName: "star.fill")
                        .resizable()
                        .scaledToFit()
                        .frame(width: 12, height: 12)
                }
                Text(weapon.skill.displayName)
                    .font(.caption2)
            }

            ForEach(weapon.traits.enumerated(), id: \.offset) { trait in
                
            }

            Text(weapon.damageLabel.lowercased())
                .rollable(isCompact: true)
                .frame(maxWidth: .infinity, alignment: .trailing)
        }
    }
}

extension Weapon {
    var damageLabel: String {
        var str = ""
        damageDice.forEach { dice in
            dice.diceRoll.forEach { roll in
                str.append("\(roll.amount)\(roll.dice.name)")
            }
            str.append(" \(dice.damageType.name)")
        }
        return str
    }
}

#Preview {
    WeaponView(weapon: .companion.javelin, isExpert: true)
}
