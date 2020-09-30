import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDividerModule } from '@angular/material/divider';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatRadioModule} from '@angular/material/radio';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

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
import { InfoTabComponent } from './info-tab/info-tab.component';
import { CircleProfileComponent } from './circle-profile/circle-profile.component';
import { CircleExploreComponent } from './circle-explore/circle-explore.component';
import { CircleUpdateComponent } from './circle-update/circle-update.component';
import { CircleRequestsComponent } from './circle-requests/circle-requests.component';
import { UpdatePostComponent } from './update-post/update-post.component'



@NgModule({
  declarations: [DashboardComponent, PostSectionComponent, CircleSectionComponent, ProfileSectionComponent, SettingsSectionComponent, ProductSectionComponent, NavigationComponent, InfoTabComponent, CircleProfileComponent, CircleExploreComponent, CircleUpdateComponent, CircleRequestsComponent, UpdatePostComponent],
  imports: [
    CommonModule,
    RouterModule,
    HeaderFooterModule,
    MatGridListModule,
    MatToolbarModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatDialogModule,
    MatMenuModule,
    MatIconModule,
    MatTooltipModule,
    MatDividerModule,
    MatSnackBarModule,
    MatRadioModule,
    MatDatepickerModule,
    MatNativeDateModule
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
