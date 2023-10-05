import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var viewModel: CreateJokeViewModel
    
    init() {
        self.viewModel = CreateJokeViewModel()
    }
    
    
    var body: some View {
        let state = viewModel.state
        
        JokeContent(state: viewModel.state, processIntent: viewModel.processIntent(intent:))
            .onAppear {
                viewModel.connect()
            }
            .onDisappear {
                viewModel.dispose()
            }
    }
}

struct JokeContent: View {
    
    var state: CreateJokeState
    var processIntent: (CreateJokeIntent)->Void
    
    var body: some View {
        if state.isLoading {
            ProgressView()
        } else {
            VStack(
                alignment: .center,
                spacing: 20
            ) {
                Text(state.generatedJoke ?? "Tap below to generate joke!")
                
                Button(
                    action:  {
                        processIntent(
                            CreateJokeIntent.RequestCreateJoke(
                                jokeType: JokeType.dad,
                                jokePrompts: ["Toddler", "Mom", "Sleep"]
                            )
                        )
                    }
                ) {
                    Text("Generate Joke")
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
