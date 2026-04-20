//
//  MasonryLayout.swift
//  iosApp
//
//  Created by Matt Gannon on 4/19/26.
//

import SwiftUI

struct MasonryLayout: Layout {
    let columns: Int
    let spacing: CGFloat

    init(columns: Int = 2, spacing: CGFloat = 12) {
        self.columns = max(1, columns)
        self.spacing = spacing
    }

    func sizeThatFits(
        proposal: ProposedViewSize,
        subviews: Subviews,
        cache: inout ()
    ) -> CGSize {
        let width = proposal.width ?? 0
        guard width > 0 else { return .zero }

        let columnWidth = columnWidth(for: width)
        var columnHeights = Array(repeating: CGFloat(0), count: columns)

        for subview in subviews {
            let height = subview.sizeThatFits(
                ProposedViewSize(width: columnWidth, height: nil)
            ).height

            let targetColumn = shortestColumn(in: columnHeights)
            columnHeights[targetColumn] += height

            if subview != subviews.last {
                columnHeights[targetColumn] += spacing
            }
        }

        return CGSize(
            width: width,
            height: columnHeights.max() ?? 0
        )
    }

    func placeSubviews(
        in bounds: CGRect,
        proposal: ProposedViewSize,
        subviews: Subviews,
        cache: inout ()
    ) {
        guard !subviews.isEmpty else { return }

        let columnWidth = columnWidth(for: bounds.width)
        var columnHeights = Array(repeating: bounds.minY, count: columns)

        for subview in subviews {
            let size = subview.sizeThatFits(
                ProposedViewSize(width: columnWidth, height: nil)
            )

            let targetColumn = shortestColumn(in: columnHeights)
            let x = bounds.minX + CGFloat(targetColumn) * (columnWidth + spacing)
            let y = columnHeights[targetColumn]

            subview.place(
                at: CGPoint(x: x, y: y),
                proposal: ProposedViewSize(width: columnWidth, height: size.height)
            )

            columnHeights[targetColumn] += size.height + spacing
        }
    }

    private func columnWidth(for totalWidth: CGFloat) -> CGFloat {
        let totalSpacing = CGFloat(columns - 1) * spacing
        return (totalWidth - totalSpacing) / CGFloat(columns)
    }

    private func shortestColumn(in heights: [CGFloat]) -> Int {
        heights.enumerated().min(by: { $0.element < $1.element })?.offset ?? 0
    }
}
