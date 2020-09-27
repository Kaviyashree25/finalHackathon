import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BankDashBoardComponent } from './bank-dash-board/bank-dash-board.component';
import { HeaderFooterModule } from '../header-footer/header-footer.module';



@NgModule({
  declarations: [BankDashBoardComponent],
  imports: [
    CommonModule,
    HeaderFooterModule
  ],
  exports: [
    BankDashBoardComponent
  ]
})
export class BankDashboardPageModule { }
