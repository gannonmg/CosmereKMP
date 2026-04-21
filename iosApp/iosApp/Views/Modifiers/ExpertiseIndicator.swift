//
//  ExpertiseIndicator.swift
//  iosApp
//
//  Created by Matt Gannon on 4/20/26.
//

import SwiftUI

struct ExpertiseIndicator: ViewModifier {
    let alignment: HorizontalAlignment

    func body(content: Content) -> some View {
        HStack(spacing: 4) {
            if alignment == .leading {
                indicatorView
                content
            } else {
                content
                indicatorView
            }
        }
    }

    var indicatorView: some View {
        Image(systemName: "star.fill")
            .resizable()
            .scaledToFit()
            .frame(width: 12, height: 12)
    }
}

extension View {
    func expertiseIndicator(alignment: HorizontalAlignment = .trailing) -> some View {
        self.modifier(ExpertiseIndicator(alignment: alignment))
    }
}
