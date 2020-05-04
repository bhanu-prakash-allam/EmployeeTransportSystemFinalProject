import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthguardserviceService } from './services/authguardservice.service';
import { AdminComponent } from './admin/admin.component';


const routes: Routes = [
  { 
    path: '', 
    redirectTo: '/home',
    pathMatch: 'full' 
  },

  { 
    path: 'login',  
  loadChildren: () => import('./login/login.module'). then(m => m.LoginComponentModule)
   },
   {
     path:'Employee',
     loadChildren: () => import('./employee/employee.module'). then(m => m.EmployeeComponentModule)

   },
   {
     path:'Admin',
     loadChildren: () => import('./admin/Admin.module'). then(m => m.AdminComponentModule)

   },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
