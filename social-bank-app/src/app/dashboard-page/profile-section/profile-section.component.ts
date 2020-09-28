import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { RouterService } from 'src/app/services/router.service';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-profile-section',
  templateUrl: './profile-section.component.html',
  styleUrls: ['./profile-section.component.css']
})
export class ProfileSectionComponent implements OnInit {
  user: any;

  constructor(private userService: UserServiceService, private routerService: RouterService) {
    
   }

  ngOnInit() {
    this.user = this.userService.getUser().subscribe(
      data=>{
        this.user=data;
      },
      error=>{
        console.error(); 
      }
    )
  }

}
