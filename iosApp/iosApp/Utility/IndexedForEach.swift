//
//  IndexedForEach.swift
//  iosApp
//
//  Created by Matt Gannon on 4/20/26.
//

import SwiftUI

/**
 Convenience ForEach alternative for items that do not conform to the Identifiable protocol.

 As offset is not a stable identity if the array is altered, should not be used in cases where frequent insertion, removal, or reordering is likely.
 */
struct IndexedForEach<Data, Content>: View where Content: View {
    private let data: [Data]
    private let content: (Data) -> Content

    init(
        _ data: [Data],
        @ViewBuilder content: @escaping (Data) -> Content
    ) {
        self.data = data
        self.content = content
    }

    var body: some View {
        ForEach(data.enumerated(), id: \.offset) { content($0.element) }
    }
}

