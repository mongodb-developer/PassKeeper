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
    
    @State var randomPassword = ""
    @State var addPassword = false
    
    @State var name:String = ""
    @State var url: String = ""
    
    @State var savedInfo = [PasswordInfo]()
    @State var selectedItemId:String = ""
    
    
    
    var repo = PassKeeperRepo()
    
    var body: some View {
        
        VStack{
            
            Text(randomPassword)
                .padding(10)
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(Color.blue, lineWidth: 2)
                )
            
            if(addPassword){
                TextField("Enter Name", text: $name)
                    .multilineTextAlignment(.center)
                    .textFieldStyle(.roundedBorder)
                    .padding(EdgeInsets(top: 0, leading: 20, bottom: 0, trailing: 20))
                    
                
                
                TextField("Enter Url",text: $url)
                    .multilineTextAlignment(.center)
                    .textFieldStyle(.roundedBorder)
                    .padding(EdgeInsets(top: 10, leading: 20, bottom: 10, trailing: 20))
                    
                
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
                        savePassword(name: name, password: randomPassword,url: url)
                        
                        name = ""
                        url = ""
                        getNewRandomPassword()
                        
                    }
                    .padding(10)
                    .overlay(
                        RoundedRectangle(cornerRadius: 8)
                            .stroke(Color.blue, lineWidth: 1)
                    )
                    
                }else{
                    Button("Refresh") {
                        getNewRandomPassword()
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
            
            Text("Saved Password")
            
            // list comes here
            List(savedInfo, id: \.self._id) { info in
                HStack {
                        VStack(alignment: .leading) {
                            Text(info.name)
                            Text(info.url).font(.subheadline).foregroundColor(.gray)
                            
                            if(selectedItemId == info._id){
                                Text(info.password)
                            }
                        }
                    
                        Spacer()
           
                        Button {
                            if(selectedItemId == info._id){
                                selectedItemId = ""
                            }else{
                                selectedItemId = info._id
                            }
                        } label: {
                            Image("eye")
                        }
                }
            }
 
        }.onAppear {
            getSavedPassword()
            getNewRandomPassword()
        }
    }
    
    func getSavedPassword(){
        repo.getAllPassword().watch { items in
            savedInfo = items as? [PasswordInfo] ?? []
        }
    }
    
    func savePassword(name: String, password: String, url: String){
        repo.saveInfo(name: name, url: url, password: password) { _, _ in
            print("saved info")
        }
    }
    
    func getNewRandomPassword(){
        randomPassword = PasswordGeneratorKt.getNewPassword(length: 13)
    }
    
    struct HomeScreenUI_Previews: PreviewProvider {
        static var previews: some View {
            HomeScreenUI()
        }
    }
    
}


