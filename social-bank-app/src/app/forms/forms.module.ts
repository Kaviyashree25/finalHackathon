import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import {BankLoginComponent} from './bank-login/bank-login.component';
import { RegisterComponent } from './register/register.component'


@NgModule({
  declarations: [LoginComponent, BankLoginComponent, RegisterComponent],
  imports: [
    CommonModule
  ],
  exports: [
    LoginComponent,
    BankLoginComponent,
    RegisterComponent
  ]
})
export class FormsModule { }
