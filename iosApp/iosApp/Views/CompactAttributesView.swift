//
//  CompactAttributesView.swift
//  iosApp
//
//  Created by Matt Gannon on 4/19/26.
//

import Shared
import SwiftUI

struct CompactAttributesView: View {

    let pairConfigs: [AttributePairView.Config]

    var body: some View {
        VStack {
            ForEach(pairConfigs, id: \.title) { config in
                HStack {
                    VStack {
                        Text(config.firstTitle).bold().frame(maxWidth: .infinity)
                        Text("\(config.firstValue)").font(.title2)
                    }

                    Text(config.title).font(.title2).bold()

                    VStack {
                        Text(config.secondTitle).bold().frame(maxWidth: .infinity)
                        Text("\(config.secondValue)").font(.title2)
                    }
                }
            }
        }
    }
}

#Preview {
    CompactAttributesView(
        pairConfigs: [
            AttributePairView.Config(
                pair: AttributePair.physical,
                firstValue: 1,
                secondValue: 3,
            ),
            AttributePairView.Config(
                pair: AttributePair.cognitive,
                firstValue: 3,
                secondValue: 2,
            ),
            AttributePairView.Config(
                pair: AttributePair.spiritual,
                firstValue: 2,
                secondValue: 1,
            )
        ]
    )
}
