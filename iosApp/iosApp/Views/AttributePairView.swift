//
//  AttributePairView.swift
//  iosApp
//
//  Created by Matt Gannon on 4/14/26.
//

import SwiftUI
import Shared

struct AttributePairView: View {

    protocol Displayable {
        var title: String { get }
        var firstTitle: String { get }
        var secondTitle: String { get }
        var firstValue: Int { get }
        var secondValue: Int { get }
        var defense: Int { get }
    }

    let displayable: any Displayable

    var body: some View {
        VStack {
            Text(displayable.title)
                .font(.title2)
                .bold()
            HStack {
                VStack {
                    Text(displayable.firstTitle)
                        .bold()
                    Text("\(displayable.firstValue)")
                        .font(.system(size: 40))
                }
                .padding(20)
                .border(.black)

                VStack {
                    Text("Defense")
                        .bold()
                    VStack {
                        Text("\(displayable.defense)")
                            .font(.system(size: 32))
                    }
                    .padding(12)
                    .border(.black)
                }

                VStack {
                    Text(displayable.secondTitle)
                        .bold()
                    Text("\(displayable.secondValue)")
                        .font(.system(size: 40))
                }
                .padding(20)
                .border(.black)
            }
        }
    }
}

#if DEBUG
struct AttributePairViewPreviewable: AttributePairView.Displayable {
    let title: String
    let firstTitle: String
    let secondTitle: String
    let firstValue: Int
    let secondValue: Int
    let defense: Int
}

#Preview {
    AttributePairView(
        displayable: AttributePairViewPreviewable(
            title: "Physical",
            firstTitle: "Strength",
            secondTitle: "Speed",
            firstValue: 1,
            secondValue: 3,
            defense: 14
        )
    )
}
#endif
