//
//  PreviewContent.swift
//  iosApp
//
//  Created by Matt Gannon on 4/16/26.
//

import Foundation
import Shared

enum PreviewContent {
    static let build1 = CharacterBuild(
        ancestry: .human,
        attributes: [
            .strength: 1,
            .speed: 3,
            .intellect: 3,
            .willpower: 2,
            .awareness: 3,
            .presence: 0
        ],
        skillRanks: [
            .agility: 2,
            .thievery: 2,
            .lore: 2,
            .crafting: 2,
            .abrasion: 1,
            .division: 1
        ]
    )
}
