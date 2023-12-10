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

#Preview{
    ContentView()
}
