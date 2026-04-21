//
//  StatSectionView.swift
//  iosApp
//
//  Created by Matt Gannon on 4/15/26.
//

import Shared
import SwiftUI


struct StatSectionView: View {

    enum Layout {
        enum Style: String, Codable {
            case stacked, paged

            var systemImage: String {
                switch self {
                case .stacked: "line.3.horizontal"
                case .paged: "ellipsis"
                }
            }
        }

        /// Estimate of a single stat section's height for paged TabView
        static let defaultPageHeight: CGFloat = 200
    }

    @AppStorage(UserDefaultKeys.statSectionLayout.rawValue) private var layoutStyle: Layout.Style = .paged
    @State private var selectedPair: AttributePair = .physical
    @State private var maxPageHeight: CGFloat = Layout.defaultPageHeight

    let build: CharacterBuild

    var body: some View {
        VStack {
            switch layoutStyle {
            case .stacked:
                attributePairSections
            case .paged:
                TabView(selection: $selectedPair) { attributePairSections }
                    .tabViewStyle(.page(indexDisplayMode: .never))
                    .frame(height: maxPageHeight)
                    .animation(.default, value: selectedPair)
                    .onPreferenceChange(PageHeightKey.self) { maxPageHeight = $0 }
                Picker("Attribute Pair", selection: $selectedPair) {
                    ForEach(AttributePair.allCases) { pair in
                        Text(pair.displayName).tag(pair)
                    }
                }
                .pickerStyle(.segmented)
                .padding(.horizontal)
            }

        }
        .overlay(alignment: .topTrailing) {
            Button("Layout", systemImage: layoutStyle.systemImage) {
                withAnimation {
                    layoutStyle = (layoutStyle == .paged) ? .stacked : .paged
                }
            }
            .buttonStyle(.glassProminent)
        }
    }

    private var attributePairSections: some View {
        ForEach(AttributePair.allCases) { pair in
            AttributePairSectionView(pair: pair, build: build)
                .padding(.horizontal)
                .background(
                    GeometryReader { proxy in
                        Color.clear
                            .preference(
                                key: PageHeightKey.self,
                                value: proxy.size.height
                            )
                    }
                )
                .tag(pair)
                .id(pair)
        }
    }

    // MARK: PageHeightLogic
    private struct PageHeightKey: PreferenceKey {
        static var defaultValue: CGFloat = Layout.defaultPageHeight
        static func reduce(value: inout CGFloat, nextValue: () -> CGFloat) {
            value = max(value, nextValue())
        }
    }
}

struct AttributePairSectionView: View {
    let pair: AttributePair
    let build: CharacterBuild

    var body: some View {
        VStack {
            AttributePairView(config: .init(
                pair: pair,
                firstValue: (build.attributes[pair.first] ?? 0).intValue,
                secondValue: (build.attributes[pair.second] ?? 0).intValue,
            ))
            let skills = Skill.Companion().skills(
                forAttributePair: pair, includeSurges: false
            )
            ForEach(skills) { skill in
                SkillRankView(config: .init(skill: skill, build: build))
            }
        }
    }
}


#Preview {
    ScrollView {
        StatSectionView(build: PreviewContent.build1)
    }
}
