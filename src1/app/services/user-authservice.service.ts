import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthserviceService {
  authenticate(username: String, password: String) {
    throw new Error("Method not implemented.");
  }

  constructor() { }
  isUserLoggedIn(): boolean{
    let user = sessionStorage.getItem('user');
    if(user == null)
      return false;
    return true;  
  }
}
