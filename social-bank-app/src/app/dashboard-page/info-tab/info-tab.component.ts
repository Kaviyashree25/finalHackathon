import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Circle } from 'src/app/model/circle';
import { User } from 'src/app/model/user';
import { CircleService } from 'src/app/services/circle.service';
import { RouterService } from 'src/app/services/router.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-info-tab',
  templateUrl: './info-tab.component.html',
  styleUrls: ['./info-tab.component.css']
})
export class InfoTabComponent implements OnInit {

  user: User;
  display=false;
  circles:Circle[]=[];

  constructor(private userService: UserService, private routerService: RouterService,private circleService:CircleService) {
    this.user = new User();this.userService.getUser().subscribe(
      data => {
        this.user = data; 
        this.circleService.getReq(this.user.userId).subscribe(
          data=>{
            this.circles=data;      
            if(this.circles.length>0){
              this.display=true;
            }      
          },
          error=>{
            console.error(); 
          }
        );       
      },
      error => {
        console.error();
      }
    )
  }

  ngOnInit() {
  
  }
  showNotification(){
    this.routerService.routeToRequests();
  }

}
