//
//  LoginView.swift
//  iosApp
//
//  Created by Mohit Sharma on 16.5.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct LoginView: View {
    
    @State var userId : String = ""
    @State var password : String = ""
    
    var body: some View {
        
        VStack(){
            
            Image("RealmLogo_dark")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 200, height: 100)
            
            
            TextField("Enter Login Id", text: $userId)
                .textFieldStyle(.roundedBorder)
                .padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 20))
                
            TextField("Password", text: $password)
                .textFieldStyle(.roundedBorder)
                .padding(EdgeInsets(top: 20, leading: 20, bottom: 20, trailing: 20))
            
            Button("Login"){
                
            }
            .padding(10)
            .overlay(RoundedRectangle(cornerRadius: 8)
                .stroke(Color.blue, lineWidth: 1)
            )
            
        }
        
    }
    
    
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView()
    }
}
