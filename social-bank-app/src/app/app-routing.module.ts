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
import { CircleProfileComponent } from './dashboard-page/circle-profile/circle-profile.component';
import { CircleExploreComponent } from './dashboard-page/circle-explore/circle-explore.component';
import { CircleUpdateComponent } from './dashboard-page/circle-update/circle-update.component';
import { CircleRequestsComponent } from './dashboard-page/circle-requests/circle-requests.component';
import { UpdatePostComponent } from './dashboard-page/update-post/update-post.component';
import { CanActivateGuard } from './can-activate.guard';



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
    path: 'bankHome',
    component: BankDashBoardComponent,
    canActivate:[CanActivateGuard]
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate:[CanActivateGuard],
    children: [
      {
        path: 'posts',
        component: PostSectionComponent,
      },
      {
        path: 'post/update',
        component: UpdatePostComponent,
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
        path: 'circleRequest',
        component: CircleRequestsComponent,
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
        path: 'circle/update',
        component: CircleUpdateComponent,
      },
      {
        path: 'circle/profile',
        component: CircleProfileComponent,
      },
      {
        path: 'circle/explore',
        component: CircleExploreComponent,
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
  },
  {
    path: '**',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
