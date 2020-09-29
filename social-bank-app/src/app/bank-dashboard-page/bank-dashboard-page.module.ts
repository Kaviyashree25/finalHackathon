import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BankDashBoardComponent } from './bank-dash-board/bank-dash-board.component';
import { HeaderFooterModule } from '../header-footer/header-footer.module';
import {MatDividerModule} from '@angular/material/divider';
import {MatTabsModule} from '@angular/material/tabs';
import {MatCardModule} from '@angular/material/card';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';



@NgModule({
  declarations: [BankDashBoardComponent],
  imports: [
    CommonModule,
    HeaderFooterModule,
    MatDividerModule,
    MatTabsModule,
    MatIconModule,
    MatCardModule,
    MatProgressBarModule,
    MatFormFieldModule,
    FormsModule,
    MatButtonModule,
    MatIconModule
  ],
  exports: [
    BankDashBoardComponent
  ]
})
export class BankDashboardPageModule { }
