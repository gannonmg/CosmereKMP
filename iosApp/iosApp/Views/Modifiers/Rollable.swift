//
//  Rollable.swift
//  iosApp
//
//  Created by Matt Gannon on 4/20/26.
//

import SwiftUI

struct Rollable: ViewModifier {
    let cornerRadius: CGFloat = 8
    let isCompact: Bool

    func body(content: Content) -> some View {
        content
            .padding(.horizontal, isCompact ? 12 : 20)
            .padding(.vertical, isCompact ? 4 : 8)
            .cornerRadius(cornerRadius)
            .overlay(
                RoundedRectangle(cornerRadius: cornerRadius)
                    .stroke(Color.black, lineWidth: 2)
            )
    }
}

extension View {
    func rollable(isCompact: Bool = false) -> some View {
        self.modifier(Rollable(isCompact: isCompact))
    }
}
