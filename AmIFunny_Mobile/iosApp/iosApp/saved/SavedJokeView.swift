//
//  SavedJokeView.swift
//  iosApp
//
//  Created by Clinton Teegarden on 11/7/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SavedJokeView: View {
    var body: some View {
        NavigationView {
            Text("Hello, Saved!")
                .navigationTitle("Saved Jokes")
        }
    }
}

#Preview {
    SavedJokeView()
}

