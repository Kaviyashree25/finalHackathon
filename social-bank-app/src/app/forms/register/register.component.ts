import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { UserAuth } from 'src/app/model/user-auth';
import { RouterService } from 'src/app/services/router.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User;
  userAuth: UserAuth;

  constructor(private userService: UserService, private routerService: RouterService) {
    this.user = new User();
    this.userAuth = new UserAuth();
  }

  ngOnInit() {
  }

  register() {
    console.log(this.user);
    this.userAuth.userId = this.user.userId;
    this.userAuth.userPassword = this.user.password;
    console.log(this.userAuth);

    this.userService.addUser(this.user).subscribe(
      data => {
        console.log(data)
      },
      error => {
        console.log(error)
      }
    );

    this.userService.addUserToAuth(this.userAuth).subscribe(
      data => {
        console.log(data)
        this.routerService.routeToLogin();
      },
      error => {
        console.log(error)
      }
    );
    
  }

}
