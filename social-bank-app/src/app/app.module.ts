import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderFooterModule } from './header-footer/header-footer.module'
import { HomePageModule } from './home-page/home-page.module'
import { DashboardPageModule } from './dashboard-page/dashboard-page.module';
import { BankDashboardPageModule } from './bank-dashboard-page/bank-dashboard-page.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from './forms/forms.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HeaderFooterModule,
    HomePageModule,
    DashboardPageModule,
    BankDashboardPageModule,
    BrowserAnimationsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
