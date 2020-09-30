import { Component, OnInit } from '@angular/core';
import { Circle } from 'src/app/model/circle';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { CircleService } from 'src/app/services/circle.service';
import { DataService } from 'src/app/services/data.service';
import { OtherCircleServiceService } from 'src/app/services/other-circle-service.service';

@Component({
  selector: 'app-circle-explore',
  templateUrl: './circle-explore.component.html',
  styleUrls: ['./circle-explore.component.css']
})
export class CircleExploreComponent implements OnInit {

  oldCircleList: Circle[];
  newCircleList: Circle[];
  otherCircles:Circle[]=[];
  circle: Circle;
  display=false;
  circleMessage: string;
  userId: string;
  reqGroup: Circle;
  constructor(private circleService: CircleService, private authService: AuthServiceService,private otherService:OtherCircleServiceService,private dataService:DataService) { 
    this.circle = new Circle();
    this.oldCircleList = [];
    this.newCircleList = [];
    this.userId = this.authService.getActiverUser();
    
  }

  ngOnInit() {
    this.otherService.shareDataSubject.subscribe(
      data=>{
        this.otherCircles=data;
      }
    )
    this.circleService.getCirclesByUserId(this.userId).subscribe(
      data=>{
        this.otherService.shareDataSubject.next(data);
      },
      error=>{
        console.error();
      }
    );
    this.circleService.getAllCircles().subscribe(
      data => {
        console.log(data);
        this.oldCircleList = data;
        for(var group of this.oldCircleList){
          if(group.createdBy !== this.userId){
            this.newCircleList.push(group);
          }
        }
      },
      error => {
        this.circleMessage = error.message;
      }
    );
  }
  joinCircle(group){
    this.circleService.joinCircle(group,this.userId).subscribe(
      data=>{
        this.otherCircles.push(data);
          this.otherService.shareDataSubject.next(this.otherCircles);
          
      },
      error=>{
        console.log(error);
        
      }

    )
  }

deleteCircle(group){
 console.log(group);
 this.circleService.leaveCircle(group,this.userId).subscribe(
   data => {
     let index = this.otherCircles.indexOf(group);
       this.otherCircles.splice(index, 1);
       this.otherService.shareDataSubject.next(this.otherCircles);
   },
   error => {
     console.log(error);
   }
 );
  }
sendCircle(group){
  console.log(group);
  this.dataService.sendDataToOtherComponent(group);
}
check(circle:Circle){
  if(circle.createdBy==this.userId){
    return false;
  }else{
    return true;
  }
}
add(group){
  this.display=true;
  this.reqGroup=group;
}
addfriend(formValue){
  this.circleService.sendReq(this.reqGroup,formValue.userId).subscribe(
    data=>{
      console.log("done");
    },
    error=>{
      console.log(error);
    }
  );
  this.display=false;
}
}
