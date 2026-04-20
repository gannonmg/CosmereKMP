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
                    MasonryLayout(columns: 2, spacing: 8) {
                        ForEach(Attribute.entries) {
                            CompactAttributeSectionView(attribute: $0, build: PreviewContent.build1)
                        }
                    }
                    .padding(.horizontal)
//                    StatSectionView(build: viewModel.build)
//                    let configs = AttributePair.entries.map { pair in
//                        AttributePairView.Config(
//                            pair: pair,
//                            firstValue: (viewModel.build.attributes[pair.first] ?? 0).intValue,
//                            secondValue: (viewModel.build.attributes[pair.second] ?? 0).intValue,
//                        )
//                    }
//                    CompactAttributesView(pairConfigs: configs)
//                    CompactSkillsView(build: viewModel.build)
                }
                .padding(.top, 32)
            }
            .background {
                UnevenRoundedRectangle(
                    cornerRadii: .init(topLeading: 20, topTrailing: 20)
                )
                .foregroundStyle(.blue.opacity(0.5))
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
        }
    }
}


struct CharacterResourcesView: View {

    let currentHealth: Int
    let maxHealth: Int
    let updateHealth: (Int) -> Void
    let currentFocus: Int
    let maxFocus: Int
    let updateFocus: (Int) -> Void
    let currentInvestiture: Int
    let maxInvestiture: Int
    let updateInvestiture: (Int) -> Void

    var body: some View {
        HStack {
            ResourceView(
                currentValue: currentHealth,
                maxValue: maxHealth,
                label: "Health",
                backgroundColor: .resourcePool(.health).opacity(0.3)
            )
            .modifier(UpdateHealthAlert(updateHealth: updateHealth))

            ResourceView(
                currentValue: currentFocus,
                maxValue: maxFocus,
                label: "Focus",
                backgroundColor: .resourcePool(.focus).opacity(0.3)
            )
            .modifier(UpdateResourcePopover(
                currentValue: currentFocus,
                updateResource: updateFocus
            ))

            ResourceView(
                currentValue: currentInvestiture,
                maxValue: maxInvestiture,
                label: "Investiture",
                backgroundColor: .resourcePool(.investiture).opacity(0.3)
            )
            .modifier(UpdateResourcePopover(
                currentValue: currentInvestiture,
                updateResource: updateInvestiture
            ))
        }
        .padding(.horizontal, 8)
        .padding(.vertical, 8)
        .glassEffect(in: .rect(cornerRadius: 16.0))
    }
}

struct UpdateHealthAlert: ViewModifier {
    @State private var showingHealthAdjuster = false
    @State private var healthUpdate: Int?

    let updateHealth: (Int) -> Void

    func body(content: Content) -> some View {
        content
            .alert("Update your health.", isPresented: $showingHealthAdjuster) {
                TextField(
                    "Health",
                    value: $healthUpdate,
                    format: .number,
                    prompt: Text("0")
                ).keyboardType(.numberPad)

                Button("Heal") {
                    updateHealth(healthUpdate ?? 0)
                    healthUpdate = nil
                }
                .tint(.green)
                .keyboardShortcut(.defaultAction)

                Button("Damage", role: .destructive) {
                    updateHealth(-(healthUpdate ?? 0))
                    healthUpdate = nil
                }
            } message: {
                Text("Hit Points")
            }
            .onTapGesture { showingHealthAdjuster = true }
    }
}

struct UpdateResourcePopover: ViewModifier {
    @State private var showingPopover: Bool = false
    private var binding: Binding<Int> {
        Binding<Int>(
            get: { currentValue },
            set: { updateResource($0) }
        )
    }

    let currentValue: Int
    let updateResource: (Int) -> Void

    func body(content: Content) -> some View {
        content
            .popover(
                isPresented: $showingPopover,
                attachmentAnchor: .point(.top),
                arrowEdge: .bottom
            ) {
                Stepper("Focus", value: binding)
                    .presentationCompactAdaptation(.popover)
                    .padding(.horizontal)
            }
            .onTapGesture { showingPopover = true }
    }
}

struct ResourceView: View {
    let currentValue: Int
    let maxValue: Int
    let label: String
    let backgroundColor: Color

    var body: some View {
        VStack {
            Text(attributedString)
            Text(label).font(.footnote)
        }
        .padding(8)
        .frame(maxWidth: .infinity)
        .background {
            RoundedRectangle(cornerRadius: 12)
                .foregroundStyle(backgroundColor)
        }
    }

    private var attributedString: AttributedString {
        let currentText = "\(currentValue)"
        let maxText = " / \(maxValue)"
        var attrString = AttributedString(currentText + maxText)

        // Apply a larger font to a specific range
        if let range = attrString.range(of: currentText) {
            attrString[range].font = .system(size: 30).bold()
        }

        // Apply a smaller font to another range
        if let range = attrString.range(of: maxText) {
            attrString[range].font = .system(size: 12)
        }
        return attrString
    }
}

struct ScalingHeaderView<Header: View, Content: View>: View {

    @ViewBuilder public var header: Header
    @ViewBuilder public var content: Content

    @State private var offset: CGFloat = 0

    var body: some View {
        ScrollView {
            header
            content
        }
        .onScrollGeometryChange(for: CGFloat.self) { geometry in
            max(0, geometry.contentOffset.y + geometry.contentInsets.top)
        } action: { oldValue, newValue in
            
        }

    }
}

#Preview {
    SheetView(viewModel: SheetViewModel(build: PreviewContent.build1))
}
