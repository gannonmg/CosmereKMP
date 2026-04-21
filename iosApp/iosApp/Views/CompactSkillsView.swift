//
//  CompactSkillsView.swift
//  iosApp
//
//  Created by Matt Gannon on 4/19/26.
//

import Shared
import SwiftUI

struct CompactSkillsView: View {
    let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]

    let build: CharacterBuild

    var body: some View {
        LazyVGrid(columns: columns, spacing: 4) {
            let skills = Skill.allCases
                .filter { !$0.isSurge || (build.skillRanks[$0]?.intValue ?? 0) > 0 }
                .sorted(by: { $0.displayName < $1.displayName })
            ForEach(skills) { skill in
                CompactSkillView(config: .init(skill: skill, build: build))
            }
        }
    }
}

struct CompactSkillView: View {
    let config: SkillRankView.Config
    var showAttribute: Bool = true

    private var attrTxt: AttributedString {
        var attrTxt = try! AttributedString(markdown: "\(config.skillName)")
        if showAttribute {
            attrTxt.append(try! AttributedString(markdown:  "**(\(config.attributeLabel.first ?? .init("")))**"))
        }
        return attrTxt
    }

    var body: some View {
        HStack {
            SkillModifierView(value: config.skillMod, isSmall: true)
            Text(attrTxt)
                .frame(maxWidth: .infinity, alignment: .leading)
        }
    }
}

#Preview {
    CompactSkillsView(build: PreviewContent.build1)
}
