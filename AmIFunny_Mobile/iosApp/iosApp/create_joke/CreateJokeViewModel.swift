//  Created by Clinton Teegarden on 9/15/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class CreateJokeViewModel : BaseiOSViewModel<CreateJokeState> {
    private let featureVM: KMPCreateJokeViewModel
    
    @Published var state: CreateJokeState
    
    override init() {
        self.featureVM = KMPCreateJokeViewModel(_scope: nil)
        self.state = featureVM.currentState
    }
    
    func processIntent(intent: CreateJokeIntent){
        featureVM.processIntent(intent:intent)
    }
    
    override func getFeatureVM() -> AnyObject {
        return self.featureVM
    }
    
    override func updateState(newState: CreateJokeState) {
        self.state = newState
    }
}

