import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Circle } from 'src/app/model/circle';
import { User } from 'src/app/model/user';
import { CircleService } from 'src/app/services/circle.service';
import { RouterService } from 'src/app/services/router.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-circle-requests',
  templateUrl: './circle-requests.component.html',
  styleUrls: ['./circle-requests.component.css']
})
export class CircleRequestsComponent implements OnInit {
  circles:Circle[]=[];
  user:User;
  constructor(private routerService:RouterService, private userService:UserService, private circleService:CircleService) { 
    this.circleService.subject.subscribe(
      data=>{
        this.circles=data;
      }
    );
    this.user = new User();this.userService.getUser().subscribe(
      data => {
        this.user = data; 
        this.circleService.getReq(this.user.userId).subscribe(
          data=>{
            this.circleService.subject.next(data);
            // if(data.length=0){
            //   location.reload;
            // }
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
  accept(circle){
    this.circleService.acceptReq(circle,this.user.userId).subscribe(
      data=>{
        if(data){
         let index=this.circles.indexOf(circle);
         this.circles.splice(index,1);
         this.circleService.subject.next(this.circles);
        }
      },
    error=>{
      console.error();
      
    }
    );
  }
  reject(circle){
    this.circleService.rejectReq(circle,this.user.userId).subscribe(
      data=>{
        if(data){
          let index=this.circles.indexOf(circle);
          this.circles.splice(index,1);
          this.circleService.subject.next(this.circles);
        }
      },
    error=>{
      console.error();
      
    }
    );
  }

}
