import { AdminComponentRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, FormControl, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    FormControl,
    ReactiveFormsModule,
    AdminComponentRoutingModule,
  ],
  declarations: [AdminComponent]
})
export class LoginComponentModule {}
