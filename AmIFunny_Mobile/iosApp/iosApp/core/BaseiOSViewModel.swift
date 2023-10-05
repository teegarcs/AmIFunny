//
//  BaseViewModel.swift
//  iosApp
//
//  Created by Clinton Teegarden on 9/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

//TODO look at protocol
//consider swiftLint AirBNB linter rules
class BaseiOSViewModel<T> : ObservableObject {
    
    private var disposables = [DisposableHandle?]()
    
    internal lazy var scope: Kotlinx_coroutines_coreCoroutineScope = {
        getCastedVM().scope
    }()
    
    private func getCastedVM() -> KMPBaseViewModel<AnyObject, AnyObject, AnyObject> {
        (getFeatureVM() as!KMPBaseViewModel<AnyObject, AnyObject, AnyObject>)
    }
    
    
    func connect() {
        flowCollector(flow: getCastedVM().viewState){ viewState in
            self.updateState(newState: viewState as! T)
        }
    }
    
    //consider getting rid of disposables
    func dispose() {
        disposables.forEach { dis in
            dis?.dispose()
        }
        
        getCastedVM().clear()
    }
    
    func flowCollector<T>(flow: CommonFlow<T>, block: @escaping (T) -> Void){
        let resultHandler = block
        let handle = flow.subscribe(coroutineScope: scope){ result in
            block(result!)
        }
        disposables.append(handle)
    }
    
    func updateState(newState: T) {
        fatalError("buildFeatureVM Not Implemented")
    }
    
    func getFeatureVM() -> AnyObject {
        fatalError("buildFeatureVM Not Implemented")
    }
    
}
