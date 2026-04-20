//
//  Untitled.swift
//  iosApp
//
//  Created by Matt Gannon on 4/20/26.
//

import Shared
import SwiftUI

struct DefenseView: View {

    struct Config {
        let iconName: String
        let color: Color
        let pairName: String
        let value: Int
    }

    let config: Config

    var body: some View {
        HStack {
            ZStack {
                Image(systemName: "shield")
                    .resizable()
                    .scaledToFit()
                    .frame(height: 48)
                Image(config.iconName)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 20, height: 20)
            }
            .foregroundStyle(config.color)

            VStack(alignment: .leading) {
                Text(config.pairName)
                    .font(.callout)
                Text("\(config.value)")
                    .font(.headline)
            }
        }
    }
}

struct DefensesView: View {

    let defenseValueFor: (AttributePair) -> Int

    private func iconName(for pair: AttributePair) -> String {
        switch pair {
        case .physical: "fist"
        case .cognitive: "brain.side"
        case .spiritual: "swirl"
        default: fatalError()
        }
    }

    var body: some View {
        HStack {
            ForEach(AttributePair.entries.enumerated(), id: \.element) { enumeration in
                if enumeration.offset != 0 {
                    Divider()
                }

                let pair = enumeration.element
                DefenseView(config: .init(
                    iconName: iconName(for: pair),
                    color: .attributePair(pair),
                    pairName: pair.displayName,
                    value: defenseValueFor(pair)
                ))
                .frame(maxWidth: .infinity)
            }
        }
    }
}


#Preview {
    DefensesView { pair in
        switch pair {
        case .physical: 14
        case .cognitive: 15
        case .spiritual: 13
        default: fatalError()
        }
    }
}
