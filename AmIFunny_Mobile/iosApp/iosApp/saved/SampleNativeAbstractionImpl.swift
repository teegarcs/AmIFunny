//
//  SampleNativeAbstractionImpl.swift
//  iosApp
//
//  Created by Clinton Teegarden on 2/20/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

/**
 * Sample of how to use the abstraction of some native framework without using expects/actuals
 */
class SampleNativeAbstractionImpl: SampleNativeAbstraction{
    
    func loadSomethingNative(completion: @escaping (String) -> Void) {
        //DO SOMETHING NATIVE!
        completion("Result")
    }
    
}
