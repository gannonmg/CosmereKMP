//
//  SkillRankView.swift
//  iosApp
//
//  Created by Matt Gannon on 4/14/26.
//

import Shared
import SwiftUI

struct SkillRankView: View {

    private enum Layout {
        /// 16
        static let circleDiameter: CGFloat = 16
        /// 16
        static let modifierCornerRadius: CGFloat = 16
    }

    protocol Displayable {
        var skillName: String { get }
        var attributeLabel: String { get }
        var attributeScore: Int { get }
        var ranks: Int { get }
        var bonusRanks: Int? { get }
    }

    let skillInfo: any Displayable

    var body: some View {
        HStack {
            Text("\(skillInfo.skillMod)")
                .bold()
                .padding(.horizontal, 20)
                .padding(.vertical, 8)
                .cornerRadius(Layout.modifierCornerRadius)
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(Color.black, lineWidth: 2)
                )
            Text(skillInfo.nameLabel)
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
        if i <= skillInfo.ranks { return .claimed }
        if i <= skillInfo.ranks + (skillInfo.bonusRanks ?? 0) {  return .granted }
        // TODO: This is a placeholder magic number for tier cap
        if 3 < i { return .ineligible }
        return .empty
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

extension SkillRankView.Displayable {
    var flatSkillMod: Int { attributeScore + ranks }
    var skillMod: Int { attributeScore + ranks + (bonusRanks ?? 0) }
    var nameLabel: String { skillName + "(" + attributeLabel + ")" }
}

#if DEBUG
struct SkillRankViewPreviewable: SkillRankView.Displayable {
    let skillName: String
    let attributeLabel: String
    let attributeScore: Int
    let ranks: Int
    let bonusRanks: Int?
}

#Preview {
    VStack {
        SkillRankView(
            skillInfo: SkillRankViewPreviewable(
                skillName: "Deduction",
                attributeLabel: "INT",
                attributeScore: 2,
                ranks: 3,
                bonusRanks: 1
            )
        )
        SkillRankView(
            skillInfo: SkillRankViewPreviewable(
                skillName: "Intimidation",
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
