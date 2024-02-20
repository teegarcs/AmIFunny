//
//  SavedJokeViewModel.swift
//  iosApp
//
//  Created by Clinton Teegarden on 2/18/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class SavedJokeViewModel : BaseiOSViewModel<SaveJokeState> {
    private let featureVM: KMPSavedJokeViewModel
    
    @Published var state: SaveJokeState
    
    override init() {
        self.featureVM = KMPSavedJokeViewModel(_scope: nil, abstraction: SampleNativeAbstractionImpl())
        self.state = featureVM.currentState
    }
    
    func processIntent(intent: SaveJokeIntent){
        featureVM.processIntent(intent:intent)
    }
    
    override func getFeatureVM() -> AnyObject {
        return self.featureVM
    }
    
    override func updateState(newState: SaveJokeState) {
        self.state = newState
    }
}
