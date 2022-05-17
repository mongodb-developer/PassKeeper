//
//  HomeScreenUI.swift
//  iosApp
//
//  Created by Mohit Sharma on 16.5.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeScreenUI: View {
    
    @State var randomPassword = "caas"
    @State var addPassword = false
    
    @State var name:String = ""
    @State var url: String = ""
    
    @State var savedInfo = [PasswordInfo]()
    
    var repo = PassKeeperRepo()
    

    
    var body: some View {
        
        VStack{
            
    
            
            if(addPassword){
                TextField("Enter Name", text: $name)
                TextField("Enter Url",text: $url)
            }else{
                
            }
            
            HStack{
                
                if(addPassword){
                    Button("cancel") {
                        self.addPassword = false
                    }
                    .padding(10)
                    .overlay(
                        RoundedRectangle(cornerRadius: 8)
                            .stroke(Color.blue, lineWidth: 1)
                    )
                    
                    
                    Button("Save") {
                        self.addPassword = !self.addPassword
                        
                        
                    }
                    .padding(10)
                    .overlay(
                        RoundedRectangle(cornerRadius: 8)
                            .stroke(Color.blue, lineWidth: 1)
                    )
                    
                }else{
                    Button("Refresh") {
                        self.addPassword = false
                    }
                    .padding(10)
                    .overlay(
                        RoundedRectangle(cornerRadius: 8)
                            .stroke(Color.blue, lineWidth: 1)
                    )
                    
                    
                    Button("Add") {
                        self.addPassword = !self.addPassword
                    }
                    .padding(10)
                    .overlay(
                        RoundedRectangle(cornerRadius: 8)
                            .stroke(Color.blue, lineWidth: 1)
                    )
                }
            }
            
            Divider().padding(20)
            
            // list comes here
            List(savedInfo, id: \.self._id) { info in
                Text(info.name)
                Text(info.password)
                Text(info.url)
            }
            
            
        }.onAppear {
            getSavedPassword()
        }
    }
    
    
    func getSavedPassword(){
        repo.getAllPassword().watch { items in
            savedInfo = items as? [PasswordInfo] ?? []
        }
    }
    
    
    func savePassword(name: String, password: String, url: String){
        
    }
    
    
    
    struct HomeScreenUI_Previews: PreviewProvider {
        static var previews: some View {
            HomeScreenUI()
        }
    }
    
}


