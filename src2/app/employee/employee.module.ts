import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, FormControl } from '@angular/forms';

import { EmployeeComponentRoutingModule } from './employee-routing.module';
import { EmployeeComponent } from './employee.component';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    FormControl,
    EmployeeComponentRoutingModule
  ],
  declarations: [EmployeeComponent]
})
export class EmployeeComponentModule {}
