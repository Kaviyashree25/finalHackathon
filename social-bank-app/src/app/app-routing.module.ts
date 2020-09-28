import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BankDashBoardComponent } from './bank-dashboard-page/bank-dash-board/bank-dash-board.component';
import { CircleSectionComponent } from './dashboard-page/circle-section/circle-section.component';
import { DashboardComponent } from './dashboard-page/dashboard/dashboard.component';
import { PostSectionComponent } from './dashboard-page/post-section/post-section.component';
import { ProductSectionComponent } from './dashboard-page/product-section/product-section.component';
import { ProfileSectionComponent } from './dashboard-page/profile-section/profile-section.component';
import { SettingsSectionComponent } from './dashboard-page/settings-section/settings-section.component';
import { LoginComponent } from './forms/login/login.component';
import { HeroPageComponent } from './home-page/hero-page/hero-page.component';
import { BankLoginComponent } from './forms/bank-login/bank-login.component';



const routes: Routes = [
  {
    path: 'home',
    component: HeroPageComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'bankLogin',
    component: BankLoginComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      {
        path: 'posts',
        component: PostSectionComponent,
      },
      {
        path: 'circles',
        component: CircleSectionComponent,
      },
      {
        path: 'products',
        component: ProductSectionComponent,
      },
      {
        path: 'profile',
        component: ProfileSectionComponent,
      },
      {
        path: 'settings',
        component: SettingsSectionComponent,
      },
      {
        path: '',
        redirectTo: 'posts',
        pathMatch: 'full'
      }
    ]
  },
   {
    path: 'bankDashboard',
    component: BankDashBoardComponent
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
