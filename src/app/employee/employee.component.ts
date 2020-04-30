import { Component, OnInit } from '@angular/core';
import{FormControl, FormBuilder,FormGroup} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  //employeeid : number;
  employeename : string;
  pickup:string;
  drop:string;
  vehicle:string;
  myFormGroup: any;

  constructor(FormBuilder : FormBuilder,public router : Router) { 

  }
  get f() {
    return this.myFormGroup.controls;
  }
  login(){
    console.log("login method");
    //this.employeeid= this.myFormGroup.controls['userid'].value;
    this.employeename=this.myFormGroup.controls['username'].value;
    this.pickup=this.myFormGroup.controls['pickup'].value;
    this.drop=this.myFormGroup.controls['drop'].values;
    this.vehicle=this.myFormGroup.controls['vehicle'].values;
  
    
      }
    

  ngOnInit(): void {
  }

}
