import SwiftUI
import shared

struct LoginScreenView: View {
	
    @State var userId : String = ""
    @State var password : String = ""
    @State var goToNextScreen : Int? = nil
    @State var showProgress = false
    
    var repo = PassKeeperRepo()
    
    var body: some View {
        
        NavigationView{
            VStack(){
                
                Image("RealmLogo_dark")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 200, height: 100)
                
                
                TextField("Username", text: $userId)
                    .textFieldStyle(.roundedBorder)
                    .padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 20))
                    
                TextField("Password", text: $password)
                    .textFieldStyle(.roundedBorder)
                    .padding(EdgeInsets(top: 20, leading: 20, bottom: 20, trailing: 20))
                
                
                Button("Login"){
                    showProgress = true
                    
                    repo.login(userName: $userId.wrappedValue, password: $password.wrappedValue) { user, error in
                        
                        showProgress = false
                        
                        if(user != nil){
                            print("login success")
                            goToNextScreen = 1
                        }else{
                            print("login failed")
                        }
                    }
                }
                .padding(10)
                .overlay(RoundedRectangle(cornerRadius: 8)
                    .stroke(Color.blue, lineWidth: 1)
                )
                
                if(showProgress){
                    ProgressView()
                }
                
                NavigationLink(destination: HomeScreenView(), tag: 1, selection: $goToNextScreen){
                        EmptyView()
                }
                
                
            }
            .offset(y:-40)
        }
        
    }
    
}





struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
            LoginScreenView()
    }
}






