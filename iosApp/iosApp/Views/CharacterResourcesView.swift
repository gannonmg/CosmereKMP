//
//  CharacterResourcesView.swift
//  iosApp
//
//  Created by Matt Gannon on 4/20/26.
//

import Shared
import SwiftUI

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
                title: "Focus",
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
                title: "Investiture",
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

    let title: String
    let currentValue: Int
    let updateResource: (Int) -> Void

    func body(content: Content) -> some View {
        content
            .popover(
                isPresented: $showingPopover,
                attachmentAnchor: .point(.top),
                arrowEdge: .bottom
            ) {
                Stepper(title, value: binding)
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
