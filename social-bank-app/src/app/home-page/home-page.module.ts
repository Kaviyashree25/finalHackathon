import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeroPageComponent } from './hero-page/hero-page.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { FormsModule } from '../forms/forms.module';
import { MatGridListModule } from '@angular/material/grid-list';



@NgModule({
  declarations: [HeroPageComponent],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatIconModule,
    RouterModule,
    FormsModule,
    MatGridListModule
  ],
  exports: [
    HeroPageComponent
  ]
})
export class HomePageModule { }
