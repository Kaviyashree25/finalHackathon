import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PostSectionComponent } from './post-section/post-section.component';
import { CircleSectionComponent } from './circle-section/circle-section.component';
import { ProfileSectionComponent } from './profile-section/profile-section.component';
import { SettingsSectionComponent } from './settings-section/settings-section.component';
import { ProductSectionComponent } from './product-section/product-section.component';
import { RouterModule } from '@angular/router';
import { HeaderFooterModule } from '../header-footer/header-footer.module';
import { NavigationComponent } from './navigation/navigation.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { InfoTabComponent } from './info-tab/info-tab.component'



@NgModule({
  declarations: [DashboardComponent, PostSectionComponent, CircleSectionComponent, ProfileSectionComponent, SettingsSectionComponent, ProductSectionComponent, NavigationComponent, InfoTabComponent],
  imports: [
    CommonModule,
    RouterModule,
    HeaderFooterModule,
    MatGridListModule
  ],
  exports: [
    DashboardComponent,
    PostSectionComponent,
    CircleSectionComponent,
    ProductSectionComponent,
    ProfileSectionComponent,
    SettingsSectionComponent
  ]
})
export class DashboardPageModule { }
