import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { UserAuth } from 'src/app/model/user-auth';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-settings-section',
  templateUrl: './settings-section.component.html',
  styleUrls: ['./settings-section.component.css']
})
export class SettingsSectionComponent implements OnInit {

  user: User;
  userAuth: UserAuth;
  userId: string;
  passwordMessage: string;
  userDetailsMessage: string;
  constructor(private authService: AuthServiceService, private userService: UserService) {
    this.user = new User();
    this.userAuth = new UserAuth();
    this.userService.getUser().subscribe(
      data => {
        this.user = data;
      },
      error => {
        console.log(error);

      }
    );
    this.userId = this.authService.getActiverUser();
  }

  ngOnInit() {
  }

  openTab(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName('tabcontent');
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = 'none';
    }
    tablinks = document.getElementsByClassName('tablinks');
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(' active', '');
    }
    document.getElementById(cityName).style.display = 'block';
    evt.currentTarget.className += ' active';
  }

  updateDetails(){
    console.log(this.user);
    this.userService.updateUser(this.user).subscribe(
      data => {
        console.log(data);
        this.userDetailsMessage = 'User Details Successfully Saved';
        location.reload();
      },
      error => {
        console.log(error);
        this.userDetailsMessage = error.message;
      }
    )
    
  }

  changePassword(value) {
    console.log(value);
    this.userService.getAuthUser().subscribe(
      data => {
        console.log(data);
        this.userAuth = data;
        console.log(this.userAuth);
        if (value.newpassword !== value.confirmpassword) {
          this.passwordMessage = 'Passwords dont match';
        }
        else if (this.userAuth.userPassword !== value.oldpassword) {
          this.passwordMessage = 'Old Password is wrong'
        } else {
          this.userAuth.userPassword = value.newpassword;
          this.userService.updatePassword(this.userAuth).subscribe(
            data => {
              console.log(data);
              this.passwordMessage = 'Password Successfully changed!'
            },
            error => {
              console.log(error);
            }
          );
        }
      },
      error => {
        console.log(error);
      }
    );


  }
}
