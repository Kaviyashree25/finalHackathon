import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BankDashBoardComponent } from './bank-dash-board/bank-dash-board.component';
import { HeaderFooterModule } from '../header-footer/header-footer.module';
import { SidenavComponent } from './sidenav/sidenav.component';



@NgModule({
  declarations: [BankDashBoardComponent, SidenavComponent],
  imports: [
    CommonModule,
    HeaderFooterModule
  ],
  exports: [
    BankDashBoardComponent
  ]
})
export class BankDashboardPageModule { }
