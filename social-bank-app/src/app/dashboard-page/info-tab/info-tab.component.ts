import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { RouterService } from 'src/app/services/router.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-info-tab',
  templateUrl: './info-tab.component.html',
  styleUrls: ['./info-tab.component.css']
})
export class InfoTabComponent implements OnInit {

  user: User;

  constructor(private userService: UserService, private routerService: RouterService) {
    this.user = new User();
  }

  ngOnInit() {
    this.userService.getUser().subscribe(
      data => {
        this.user = data;
        console.log(this.user);
        
      },
      error => {
        console.error();
      }
    )
  }

}
