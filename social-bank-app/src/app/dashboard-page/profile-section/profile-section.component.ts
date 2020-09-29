import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { RouterService } from 'src/app/services/router.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile-section',
  templateUrl: './profile-section.component.html',
  styleUrls: ['./profile-section.component.css']
})

export class ProfileSectionComponent implements OnInit {
  user: User;
  password: string;

  constructor(private userService: UserService, private routerService: RouterService) {
    this.user = new User();
    this.userService.getUser().subscribe(
      data => {
        this.user = data;
        console.log(this.user);
        
      },
      error => {
        console.error();
      }
    );
    this.userService.getAuthUser().subscribe(
      data => {
        console.log(JSON.parse(JSON.stringify(data)).userPassword);
        this.password = JSON.parse(JSON.stringify(data)).userPassword;
        
      },
      error => {
        console.error();
      }
    );

  }

  ngOnInit() {
   
  }

}
