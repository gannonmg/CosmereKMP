//
//  CosmereTabView.swift
//  iosApp
//
//  Created by Matt Gannon on 4/19/26.
//

import SwiftUI

struct CosmereTabView: View {
    var body: some View {
        TabView {
            Tab("Sheet", systemImage: "waveform.path.ecg.text.clipboard") {
                SheetView(viewModel: .init(build: PreviewContent.build1))
            }
            Tab("Inventory", systemImage: "backpack") {

            }
            Tab("Talents", systemImage: "trophy") {

            }
            Tab("Notes", systemImage: "pencil.and.scribble") {

            }
        }
    }
}

#Preview {
    CosmereTabView()
}
