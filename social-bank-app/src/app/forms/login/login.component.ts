import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { UserAuth } from 'src/app/model/user-auth';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { RouterService } from 'src/app/services/router.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: UserAuth;

  constructor(private authService: AuthServiceService, private routerService: RouterService) {
    this.user = new UserAuth();
  }

  ngOnInit() {
  }

  login(user) {
    this.user.userId = user.username;
    this.user.userPassword = user.password;
    const token = this.authService.authenticateUser(this.user).subscribe(
      data => {
        const token = data['token'];
        console.log(token);
        if (token !== null) {
          this.authService.setActiveUser(this.user.userId);
          this.authService.setBearerToken(token);
          this.routerService.routeToDashboard();
        }
        else {
          alert('Wrong Credentials, Try again!');
          this.routerService.routeToLogin();
        }
      },
      error => {
        console.log(error.message);
      }
    );
  }

  back() {
    this.routerService.routeBack();
  }
}
