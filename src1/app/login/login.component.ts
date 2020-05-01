import { Component, OnInit } from '@angular/core';
import {FormControl, FormBuilder, FormGroup} from '@angular/forms';
import { Router } from '@angular/router';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: String;
  Password: String;
   myFormGroup: FormGroup;

  constructor(FormBuilder: FormBuilder) {

  }
  get f() {
    return this.myFormGroup.controls;
  }
  login1() {
    console.log('login method');
    this.username = this.myFormGroup.controls.username.value;
    this.Password = this.myFormGroup.controls.password.value;
  }

  ngOnInit(): void {
  }

}
