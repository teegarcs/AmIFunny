//
//  BaseViewModel.swift
//  iosApp
//
//  Created by Clinton Teegarden on 9/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync

//TODO look at protocol
//consider swiftLint AirBNB linter rules
class BaseiOSViewModel<T> : ObservableObject {
    
    private func getCastedVM() -> KMPBaseViewModel<AnyObject, AnyObject, AnyObject> {
        (getFeatureVM() as!KMPBaseViewModel<AnyObject, AnyObject, AnyObject>)
    }
    
    func connect() async throws {
        let sequence = asyncSequence(for: getCastedVM().viewStateFlow)
        for try await viewState in sequence {
            DispatchQueue.main.async {
                self.updateState(newState: viewState as! T)
            }
        }
    }
    
    func clear() {
        getCastedVM().clear()
    }
    
    func updateState(newState: T) {
        fatalError("buildFeatureVM Not Implemented")
    }
    
    func getFeatureVM() -> AnyObject {
        fatalError("buildFeatureVM Not Implemented")
    }
    
}
