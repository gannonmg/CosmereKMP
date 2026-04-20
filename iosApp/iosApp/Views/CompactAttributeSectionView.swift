//
//  CompactAttributeSectionView.swift
//  iosApp
//
//  Created by Matt Gannon on 4/19/26.
//

import Shared
import SwiftUI

struct CompactAttributeSectionView: View {
    let attribute: Attribute
    let build: CharacterBuild

    var body: some View {
        VStack(alignment: .leading) {
            Text(
                "\(attribute.displayName): \(build.attributes[attribute]?.intValue ?? 0)"
            ).bold()
            let skills = attribute
                .associatedSkills(includeSurges: true)
                .filter { !$0.isSurge || build.skillRanks[$0]?.intValue ?? 0 > 0 }
            ForEach(skills) { skill in
                CompactSkillView(
                    config: .init(skill: skill, build: build),
                    showAttribute: false
                )
            }
        }
        .padding()
        .background {
            RoundedRectangle(cornerRadius: 16)
                .foregroundStyle(.white.opacity(0.9))
                .shadow(radius: 2, x: 1, y: 2)
        }
    }
}

#Preview {
    ScrollView {
        MasonryLayout(columns: 2, spacing: 12) {
            ForEach(Attribute.entries) {
                CompactAttributeSectionView(attribute: $0, build: PreviewContent.build1)
            }
        }
        .padding(12)
    }
}
