//
//  AttributePairView.swift
//  iosApp
//
//  Created by Matt Gannon on 4/14/26.
//

import SwiftUI
import Shared

struct AttributePairView: View {

    struct Config {
        let title: String
        let firstTitle: String
        let secondTitle: String
        let firstValue: Int
        let secondValue: Int
        let defense: Int

        init(
            pair: AttributePair,
            firstValue: Int,
            secondValue: Int,
        ) {
            self.title = pair.displayName
            self.firstTitle = pair.first.displayName
            self.secondTitle = pair.second.displayName
            self.firstValue = firstValue
            self.secondValue = secondValue
            self.defense = 10 + firstValue + secondValue
        }
    }

    let config: Config

    var body: some View {
        VStack {
            Text(config.title)
                .font(.title2)
                .bold()
            HStack {
                AttributeValueBox(
                    title: config.firstTitle,
                    value: config.firstValue,
                    first: true
                )

                VStack {
                    Text("Defense")
                        .bold()
                    VStack {
                        Text("\(config.defense)")
                            .font(.system(size: 32))
                    }
                    .padding(.vertical, 8)
                    .padding(.horizontal, 16)
                    .border(.black)
                }

                AttributeValueBox(
                    title: config.secondTitle,
                    value: config.secondValue,
                    first: false
                )
            }
        }
    }


    struct AttributeValueBox: View {
        let title: String
        let value: Int
        let first: Bool

        private enum Layout {
            static let cornerRadius: CGFloat = 16
        }

        var body: some View {
            VStack {
                Text(title)
                    .bold()
                Text("\(value)")
                    .font(.system(size: 40))
            }
            .frame(maxWidth: .infinity)
            .padding(.vertical, 12)
            .overlay(
                UnevenRoundedRectangle(cornerRadii: .init(
                    topLeading: first ? Layout.cornerRadius : 0,
                    bottomLeading: first ? Layout.cornerRadius : 0,
                    bottomTrailing: first ? 0 : Layout.cornerRadius,
                    topTrailing: first ? 0 : Layout.cornerRadius
                ))
                .stroke(.blue, lineWidth: 2)
            )
        }
    }
}

#Preview {
    AttributePairView(
        config: AttributePairView.Config(
            pair: AttributePair.physical,
            firstValue: 1,
            secondValue: 3,
        )
    )
}
