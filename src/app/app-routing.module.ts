import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { EmployeeComponent } from './employee/employee.component';
import { AdminComponent } from './admin/admin.component';


const routes: Routes = [
  {path : '', redirectTo:'login',pathMatch:"full"},
  {path :'login' , component : LoginComponent},
  {path :'employee' , component : EmployeeComponent},
  {path:'Admin',component:AdminComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
