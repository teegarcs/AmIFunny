//
//  CreateJokeView.swift
//  iosApp
//
//  Created by Clinton Teegarden on 11/7/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CreateJokeView: View {
    
    @ObservedObject var viewModel: CreateJokeViewModel
    
    init() {
        self.viewModel = CreateJokeViewModel()
    }
    
    var body: some View {
        JokeContent(
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

struct JokeContent: View {
    
    var state: CreateJokeState
    var processIntent: (CreateJokeIntent)->Void
    
    @State private var jokeEntry = ""
    @State private var jokeTypeSelection = 0
    
    var body: some View {
        NavigationView {
            
            ScrollView {
                VStack{
                    
                    Text("Type nouns that you would like to appear in your joke.")
                        .padding(24)
                
                    TextField("Joke Nouns", text: $jokeEntry)
                        .padding(.horizontal, 32)
                    
                    //TODO figure out how to make label show "Joke Type"
                    Picker("Joke Type", selection: $jokeTypeSelection) {
                        ForEach(0..<state.jokeTypes.count, id: \.self) { index in
                            Text(state.jokeTypes[index])
                        }
                    }
                    
                    if(!state.isLoading){
                        Button("Generate Joke"){
                            processIntent(
                                CreateJokeIntent.RequestCreateJoke(
                                    jokeType: state.jokeTypes[jokeTypeSelection],
                                    jokePrompt: jokeEntry
                                )
                            )
                        }
                        .padding(.top, 16)
                    }else{
                        ProgressView()
                            .padding(.top, 16)
                        Text("Generating Joke")
                    }
                    
                    if(state.generatedJoke?.isEmpty == false){
                        
                        ZStack{
                            Rectangle()
                                .foregroundColor(.white)
                                .cornerRadius(10)
                                .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                            VStack{
                                Text(state.generatedJoke ?? "")
                                    .font(.subheadline)
                                    .padding(16)
                                
                                HStack{
                                    Spacer()
                                    let buttonLabel = if(state.jokeSaved){
                                        "Saved"
                                    }else{
                                       "Save"
                                    }
                                    
                                    Button(buttonLabel){
                                        processIntent(CreateJokeIntent.SaveCurrentJoke())
                                    }
                                    .disabled(state.jokeSaved)
                                    .padding()
                                }
                            }
                                
                        }
                        .padding(16)
                        .padding(.top, 16)
                        
                    }
                }
            }
            .navigationTitle("Create Joke")
                
        }
    }
}

/**
 PREVIEWS
 */
#Preview("Joke Not Saved") {
    let state = CreateJokeState(
        isLoading: false,
        generatedJoke: "Some Generated Joke",
        jokeSaved: false,
        jokeTypes: [JokeType.dad.name, JokeType.corny.name, JokeType.knock.name]
    )
    
    return JokeContent(state:state){ _ in
        
    }
}

#Preview("Joke Loading") {
    let state = CreateJokeState(
        isLoading: true,
        generatedJoke: nil,
        jokeSaved: false,
        jokeTypes: [JokeType.dad.name, JokeType.corny.name, JokeType.knock.name]
    )
    
   return JokeContent(state:state){ _ in
        
    }
}


#Preview("Joke Saved") {
    let state = CreateJokeState(
        isLoading: false,
        generatedJoke: "Some Generated Joke",
        jokeSaved: true,
        jokeTypes: [JokeType.dad.name, JokeType.corny.name, JokeType.knock.name]
    )
    
    return JokeContent(state:state){ _ in
        
    }
}


#Preview("Default") {
    let state = CreateJokeState(
        isLoading: false,
        generatedJoke: nil,
        jokeSaved: false,
        jokeTypes: [JokeType.dad.name, JokeType.corny.name, JokeType.knock.name]
    )
    
    return JokeContent(state:state){ _ in
        
    }
}
