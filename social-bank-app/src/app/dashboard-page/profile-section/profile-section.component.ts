import { Component, OnInit } from '@angular/core';
import { RouterService } from 'src/app/services/router.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile-section',
  templateUrl: './profile-section.component.html',
  styleUrls: ['./profile-section.component.css']
})

export class ProfileSectionComponent implements OnInit {
  user: any;

  constructor(private userService: UserService, private routerService: RouterService) {

  }

  ngOnInit() {
    this.user = this.userService.getUser().subscribe(
      data => {
        this.user = data;
      },
      error => {
        console.error();
      }
    )
  }

}
