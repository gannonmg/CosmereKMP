//
//  SkillRankView.swift
//  iosApp
//
//  Created by Matt Gannon on 4/14/26.
//

import Shared
import SwiftUI

struct SkillRankView: View {

    enum Layout {
        /// 16
        static let circleDiameter: CGFloat = 16
    }

    struct Config {
        let skill: Skill
        let attributeLabel: String
        let attributeScore: Int
        let ranks: Int
        var bonusRanks: Int? = 0
    }

    let config: Config

    var body: some View {
        HStack {
            SkillModifierView(value: config.skillMod)
            Text(config.nameLabel)
            Spacer()
            ForEach(1...5, id: \.self) {
                let state = getState(i: $0)
                Circle()
                    .skillRankStateStyle(rankState: state)
                    .frame(width: Layout.circleDiameter)
            }
        }
    }

    private func getState(i: Int) -> SkillRankState {
        if i <= config.ranks { return .claimed }
        if i <= config.ranks + (config.bonusRanks ?? 0) {  return .granted }
        // TODO: This is a placeholder magic number for tier cap
        if 3 < i { return .ineligible }
        return .empty
    }
}

struct SkillModifierView: View {
    let value: Int
    var isSmall: Bool = false

    var body: some View {
        Text("\(value)")
            .font(isSmall ? .callout : .body)
            .bold()
            .rollable(isCompact: isSmall)
    }
}

extension Shape {
    func skillRankStateStyle(rankState: SkillRankState) -> some View {
        switch rankState {
        case .empty: AnyView(self.stroke(lineWidth: 2))
        case .claimed: AnyView(self.fill(.black))
        case .granted: AnyView(self.fill(.blue))
        case .ineligible: AnyView(self.fill(.gray))
        case .available: AnyView(self.stroke(.yellow, lineWidth: 2))
        case .selected: AnyView(self.fill(.yellow))
        default: AnyView(self)
        }
    }
}

extension SkillRankView.Config {
    var flatSkillMod: Int { attributeScore + ranks }
    var skillMod: Int { attributeScore + ranks + (bonusRanks ?? 0) }
    var nameLabel: String { skillName + "(" + attributeLabel + ")" }
    var skillName: String { skill.displayName }

    init(skill: Skill, build: CharacterBuild) {
        let attribute = skill.associatedAttribute

        self.init(
            skill: skill,
            attributeLabel: attribute.shortLabel,
            attributeScore: (build.attributes[attribute] ?? 0).intValue,
            ranks: (build.skillRanks[skill] ?? 0).intValue
        )
    }
}

#if DEBUG

#Preview {
    VStack {
        SkillRankView(
            config: .init(
                skill: .deduction,
                attributeLabel: "INT",
                attributeScore: 2,
                ranks: 3,
                bonusRanks: 1
            )
        )
        SkillRankView(
            config: .init(skill: .deduction, build: PreviewContent.build1)
        )
        SkillRankView(
            config: .init(
                skill: .intimidation,
                attributeLabel: "WIL",
                attributeScore: 1,
                ranks: 1,
                bonusRanks: 0
            )
        )
    }
    .padding()
}
#endif
