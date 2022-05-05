import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greeting()
    
    @State var randomPassword = PasswordGeneratorKt.getNewPassword(length: 13)

	var body: some View {
        
        VStack{
            
            Text(randomPassword)
            
            Button("Refresh") {
                self.randomPassword = PasswordGeneratorKt.getNewPassword(length: 13)
            }
            .padding(10)
            .overlay(
               RoundedRectangle(cornerRadius: 8)
                   .stroke(Color.blue, lineWidth: 1)
           )
        }
        
        
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        Group {
            ContentView()
        }
	}
}
