import SwiftUI

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            SheetView(viewModel: SheetViewModel(build: PreviewContent.build1))
        }
    }
}
