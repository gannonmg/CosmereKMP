//
//  Color+Extension.swift
//  iosApp
//
//  Created by Matt Gannon on 4/20/26.
//

import Shared
import SwiftUI

extension Color {
    static func attributePair(_ attributePair: AttributePair) -> Color {
        switch attributePair {
        case .physical: Color("WHBlue")
        case .cognitive: Color("WHPurple")
        case .spiritual: Color("WHGreen")
        }
    }

    static func resourcePool(_ resource: ResourcePool) -> Color {
        switch resource {
        case .health: Color("WHRed")
        case .focus: Color("WHBlue")
        case .investiture: Color("WHGreen")
        }
    }
}
