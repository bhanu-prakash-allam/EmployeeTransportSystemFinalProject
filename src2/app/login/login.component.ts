import { Component, OnInit } from '@angular/core';
import{FormControl, FormBuilder,FormGroup} from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthserviceService } from '../services/user-authservice.service';
//import { UserAuthserviceService } from 'app/services/user-authservice.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username:string;
  password:string;
  submitted=false;
  errorMessage : string;
  autherized : boolean;
   myFormGroup: any;
  
  constructor(FormBuilder :FormBuilder,public auth:UserAuthserviceService,public router : Router) { 
    this.myFormGroup=FormBuilder.group({
     
      "username":new FormControl(""),
          "password": new FormControl("")
     });
     this.errorMessage = "Invalid Credentials!!!";
     this.autherized = true;
     console.log("in form builder of login");
  
  }
  get f(){
    return this.myFormGroup.controls;
  }
  login1(){
    console.log("login method");
    this.username= this.myFormGroup.controls['username'].value;
    this.password=this.myFormGroup.controls['password'].value;
    this.submitted = true;
    this.auth.authenticate(this.username, this.password).subscribe(
      // success function
      successData=>{
        console.log("SUCCESS...");
        console.log(successData);
        this.autherized = true;
        this.router.navigate(['/employee']);
      },
      // failure function
      failureData => {
        console.log("FAILED!!!");
        this.autherized = false;
      }
    );
  console.log(this.auth.authenticate(this.username,this.password));
   console.log("Username : "+this.username+"\n"+"Password : "+this.password);
   }

  
 
  ngOnInit(): void {
  }

}
