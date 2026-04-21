//
//  CharacterInfoView.swift
//  iosApp
//
//  Created by Matt Gannon on 4/16/26.
//

import Shared
import SwiftUI

@Observable
final class SheetViewModel {
    private(set) var currentHealth: Int = 16
    private(set) var currentFocus: Int = 4
    private(set) var currentInvestiture: Int = 3
    let maxHealth: Int = 16
    let maxFocus: Int = 4
    let maxInvestiture: Int = 5

    let build: CharacterBuild

    init(build: CharacterBuild) {
        self.build = build
    }

    func defense(for pair: AttributePair) -> Int {
        let firstValue = (build.attributes[pair.first] ?? 0).intValue
        let secondValue = (build.attributes[pair.second] ?? 0).intValue
        let defense = 10 + firstValue + secondValue
        return defense
    }

    func attributeScore(_ attribute: Attribute) -> Int {
        build.attributes[attribute]?.intValue ?? 0
    }

    func skillModifier(_ skill: Skill) -> Int {
        let attributeMod = attributeScore(skill.associatedAttribute)
        let rank = build.skillRanks[skill]?.intValue ?? 0
        return attributeMod + rank
    }

    func updateHealth(by amount: Int) {
        let newValue = currentHealth + amount
        self.currentHealth = min(max(0, newValue), maxHealth)
    }

    func updateFocus(to newValue: Int) {
        self.currentFocus = min(max(0, newValue), maxFocus)
    }

    func updateInvestiture(to newValue: Int) {
        self.currentInvestiture = min(max(0, newValue), maxInvestiture)
    }
}

struct SheetView: View {

    // TODO: Remove once CharacterSheet is more fleshed out
    @State var viewModel: SheetViewModel

    @AppStorage(UserDefaultKeys.statSectionLayout.rawValue) private var layoutStyle: StatSectionView.Layout.Style = .paged


    var body: some View {
        NavigationStack {
            ScrollView {
                VStack {
                    DefensesView(defenseValueFor: viewModel.defense(for:))
                        .frame(maxWidth: .infinity)
                        .padding(.horizontal)

                    HStack(spacing: 4) {
                        HStack {
                            ZStack {
                                Image(systemName: "shield")
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 28, height: 28)
                                Image("bounce")
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 32, height: 32)
                                    .offset(y: -10)
                            }
                            VStack(alignment: .leading, spacing: 0) {
                                Text("Deflect")
                                    .font(.caption2)
                                Text("1")
                                    .font(.headline)
                            }
                        }
                        .padding(12)
                        .background {
                            RoundedRectangle(cornerRadius: 16)
                                .foregroundStyle(.white)
                                .shadow(radius: 2, x: 1, y: 2)
                        }

                        HStack {
                            HStack {
                                Image(systemName: "figure.run")
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 28, height: 28)
                                VStack(alignment: .leading, spacing: 0) {
                                    Text("Movement")
                                        .font(.caption2)
                                    Text("30ft.")
                                        .font(.headline)
                                }
                            }

                            HStack {
                                Image(systemName: "eye")
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 28, height: 28)
                                VStack(alignment: .leading, spacing: 0) {
                                    Text("Senses")
                                        .font(.caption2)
                                    Text("10ft.")
                                        .font(.headline)
                                }
                            }
                        }
                        .padding(12)
                        .background {
                            RoundedRectangle(cornerRadius: 16)
                                .foregroundStyle(.white)
                                .shadow(radius: 2, x: 1, y: 2)
                        }

                        VStack(spacing: 0) {
                            Text("Conditions")
                                .font(.caption2)
                            Image(systemName: "eye")
                                .resizable()
                                .scaledToFit()
                                .frame(width: 24, height: 24)
                        }
                        .padding(12)
                        .background {
                            RoundedRectangle(cornerRadius: 16)
                                .foregroundStyle(.white)
                                .shadow(radius: 2, x: 1, y: 2)
                        }
                    }

                    CollapsibleSection("Skills") {
                        MasonryLayout(columns: 2, spacing: 8) {
                            ForEach(Attribute.allCases) {
                                CompactAttributeSectionView(attribute: $0, build: PreviewContent.build1)
                            }
                        }
                    }
                    .padding(.horizontal)

                    CollapsibleSection("Weapons") {
                        let weapons: [Weapon] = [
                            Weapon.companion.javelin,
                            Weapon.companion.dagger,
                            Weapon.companion.shortbow,
                            Weapon.companion.staff,
                            Weapon.companion.greatsword
                        ]

                        VStack(spacing: 4) {
                            IndexedForEach(weapons) { weapon in
                                WeaponView(
                                    weapon: weapon,
                                    skillModifier: viewModel.skillModifier(weapon.skill),
                                    isExpert: .random()
                                )
                            }
                        }
                        .padding(.bottom, 4)
                    }
                    .padding(.horizontal)
                }
                .padding(.top, 32)
            }
            .ignoresSafeArea(.all, edges: .bottom)
            .navigationTitle("Kayle")
            .navigationSubtitle("Agent 1 ✪, Scholar 2, Dustbringer 1")
            .navigationBarTitleDisplayMode(.inline)
            .overlay(alignment: .bottom) {
                CharacterResourcesView(
                    currentHealth: viewModel.currentHealth,
                    maxHealth: viewModel.maxHealth,
                    updateHealth: viewModel.updateHealth(by:),
                    currentFocus: viewModel.currentFocus,
                    maxFocus: viewModel.maxFocus,
                    updateFocus: viewModel.updateFocus(to:),
                    currentInvestiture: viewModel.currentInvestiture,
                    maxInvestiture: viewModel.maxInvestiture,
                    updateInvestiture: viewModel.updateInvestiture(to:)
                )
                .padding(.horizontal)
                .padding(.bottom)
            }
            .background(.blue.opacity(0.1))
        }
    }
}

struct CollapsibleSection<Content: View>: View {

    private let title: String
    @State private var isOpen: Bool
    private let content: () -> Content

    init(
        _ title: String,
        isOpen: Bool = true,
        @ViewBuilder content: @escaping () -> Content
    ) {
        self.title = title
        self.isOpen = isOpen
        self.content = content
    }

    var body: some View {
        VStack {
            HStack {
                Text(title).font(.headline)
                Spacer()
                Button {
                    withAnimation {
                        isOpen.toggle()
                    }
                } label: {
                    let systemImage = isOpen ? "arrow.down" : "arrow.right"
                    Image(systemName: systemImage)
                }
                .foregroundStyle(.black)
            }

            if isOpen {
                content()
                    .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .clipped()
    }
}

#Preview {
    SheetView(viewModel: SheetViewModel(build: PreviewContent.build1))
}
