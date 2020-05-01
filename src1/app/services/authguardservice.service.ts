import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserAuthserviceService } from './user-authservice.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardserviceService {

  constructor(public auth:UserAuthserviceService,public router :Router) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if(this.auth.isUserLoggedIn())
    return true;
else{
  // navigate to login component
  this.router.navigate(['/login']);
  return false;
}
  }
}
