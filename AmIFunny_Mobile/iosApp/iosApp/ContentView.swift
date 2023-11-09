import SwiftUI
import shared


struct ContentView: View {
    var body: some View {
        BottomNavigation()
    }
}

struct BottomNavigation : View {
    var body: some View {
        TabView{
            CreateJokeView()
                .tabItem {
                    Label("Create", systemImage: "face.smiling")
                }
            
            SavedJokeView()
                .tabItem {
                    Label("Saved", systemImage: "archivebox")
                }
            
        }
    }
}

//struct ContentView: View {
//    @ObservedObject var viewModel: CreateJokeViewModel
//    
//    init() {
//        self.viewModel = CreateJokeViewModel()
//    }
//    
//    var body: some View {
//        JokeContent(state: viewModel.state, processIntent: viewModel.processIntent(intent:))
//            .task {
//                try? await viewModel.connect()
//            }
//            .onDisappear {
//                viewModel.clear()
//            }
//    }
//}

//struct JokeContent: View {
//    
//    var state: CreateJokeState
//    var processIntent: (CreateJokeIntent)->Void
//    
//    var body: some View {
//        if state.isLoading {
//            ProgressView()
//        } else { 
//            VStack(
//                alignment: .center,
//                spacing: 20
//            ) {
//                Text(state.generatedJoke ?? "Tap below to generate joke!")
//                
//                Button(
//                    action:  {
////                        processIntent(
////                            CreateJokeIntent.RequestCreateJoke(
////                                jokeType: JokeType.dad,
////                                jokePrompts: ["Toddler", "Mom", "Sleep"]
////                            )
////                        )
//                    }
//                ) {
//                    Text("Generate Joke")
//                }
//            }
//        }
//    }
//}

#Preview{
    ContentView()
}
