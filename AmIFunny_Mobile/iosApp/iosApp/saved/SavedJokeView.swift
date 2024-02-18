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
    @ObservedObject var viewModel: SavedJokeViewModel
    
    init() {
        self.viewModel = SavedJokeViewModel()
    }
    
    var body: some View {
        SavedJokeContent(
            state: viewModel.state,
            processIntent: viewModel.processIntent(intent:)
        )
        .onAppear{
            //NEED to re attach?
        }
        .task {
            try? await viewModel.connect()
        }
    }
}

struct SavedJokeContent: View {
    
    var state: SaveJokeState
    var processIntent: (SaveJokeIntent)->Void
    
    var body: some View {
        NavigationView {
            if(state.isLoading){
                LoadingState()
            }else if(state.jokes.isEmpty){
                EmptyState()
            }else{
                JokeList(
                    jokes: state.jokes,
                    processIntent: processIntent
                )
            }
        }
        .navigationTitle("Saved Jokes")
        
    }
}

struct LoadingState: View {
    var body: some View {
        VStack{
            ProgressView()
                .padding(.top, 16)
            Text("Loading Saved Jokes")
        }
    }
}
struct EmptyState: View {
    
    var body: some View {
        VStack{
            Text("No Saved Jokes")
        }
    }
    
}
struct JokeList: View {
    var jokes: [SavedJoke]
    var processIntent: (SaveJokeIntent)->Void
    
    var body: some View {
        ScrollView{
            LazyVStack {
                ForEach(jokes, id: \.self) { item in
                    JokeItemContent(joke:item, processIntent: processIntent)
                }
            }
        }
    }
}
struct JokeItemContent: View {
    var joke: SavedJoke
    var processIntent: (SaveJokeIntent)->Void
    
    var body: some View {
        VStack {
            HStack(alignment: .top) {
                Spacer() // Pushes Icon to the end
                Button(action: {
                    processIntent(SaveJokeIntent.DeleteJoke(joke: joke))
                }) {
                    Image(systemName: "xmark")
                        .accessibility(label: Text("delete"))
                }
            }
            .padding(.trailing, 16)
            .padding(.top, 8)
            
            VStack(alignment: .leading) {
                Text(joke.joke)
                Text(joke.nouns)
                    .font(.system(size: 12))
                    .frame(maxWidth: .infinity, alignment: .trailing)
                    .padding(.top, 4)
            }
            .padding(16)
            
            Divider()
        }
    }
}


#Preview("Saved Joke Loading") {
    let state = SaveJokeState(
        jokes: [],
        isLoading: true
    )
    
    return SavedJokeContent(state:state){ _ in}
}

#Preview("Saved Joke Empty") {
    let state = SaveJokeState(
        jokes: [],
        isLoading: false
    )
    
    return SavedJokeContent(state:state){ _ in}
}

#Preview("Saved Joke List") {
    
    let joke = SavedJoke(joke: "Some joke", nouns: "Noun1, Noun2")
    let joke2 = SavedJoke(joke: "Some joke2", nouns: "Noun1, Noun2")
    let joke3 = SavedJoke(joke: "Some joke3", nouns: "Noun1, Noun2")
    
    let jokeList = [joke, joke2, joke3]
    
    let state = SaveJokeState(
        jokes: jokeList,
        isLoading: false
    )
    
    return SavedJokeContent(state:state){ _ in}
}


