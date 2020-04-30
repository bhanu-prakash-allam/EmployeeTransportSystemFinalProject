import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationServiceService {

  constructor() { }
  //  check userid/pass and authenticate 
  authenticate(username : string, password : string): any{
    // hard-coded validation
    if(username === "First" && password === "abc"){
      //  need to maintain session
      // sessionStorage object is auto available
      sessionStorage.setItem("user", username);
      return true;
    }else{
      return false;
    }
  }

  // to check if user if logged in or not
  isUserLoggedIn(): boolean{
    // check if sessionStorage contains key 'user'
    let user = sessionStorage.getItem('user');
    if(user == null)
      return false;
    return true;  
  }

  

  // get user Details
  getUserDetails():string{
    let user = sessionStorage.getItem('user');
    return user;
  }

}
