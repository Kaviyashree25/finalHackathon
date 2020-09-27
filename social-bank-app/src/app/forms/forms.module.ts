import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { BankLoginComponent } from './bank-login/bank-login.component';



@NgModule({
  declarations: [LoginComponent, BankLoginComponent],
  imports: [
    CommonModule
  ],
  exports: [
    LoginComponent,
    BankLoginComponent
  ]
})
export class FormsModule { }
