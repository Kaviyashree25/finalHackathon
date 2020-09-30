import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Circle } from 'src/app/model/circle';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { CircleService } from 'src/app/services/circle.service';
import { DataService } from 'src/app/services/data.service';
import { OtherCircleServiceService } from 'src/app/services/other-circle-service.service';

@Component({
  selector: 'app-circle-section',
  templateUrl: './circle-section.component.html',
  styleUrls: ['./circle-section.component.css']
})
export class CircleSectionComponent implements OnInit {

  public circle: Circle;
  public oldCircleList: Circle[];
  public newCirclelist: Circle[];
  public noOfMembers: number;
  circleMessage: string;
  userId: string;
  otherCircles: Circle[];

  constructor(private circleService: CircleService, private authService: AuthServiceService, private dataService: DataService,private router:Router,private otherService:OtherCircleServiceService) {
    this.circle = new Circle();
    this.oldCircleList = [];
    this.newCirclelist = [];
    this.userId = this.authService.getActiverUser();

    this.router.routeReuseStrategy.shouldReuseRoute = () => {
      return false;
    };
    this.otherService.shareDataSubject.subscribe(
      data=>{
        this.otherCircles=data;
      }
    )
   }

  ngOnInit() {
    this.circleService.getAllCircles().subscribe(
      data => {
        console.log(data);
        this.oldCircleList = data;
        for(var group of this.oldCircleList){
          if(group.createdBy === this.userId){
            this.newCirclelist.push(group);
          }
        }
        console.log(this.newCirclelist);
        
        if(this.newCirclelist.length === 0){
          this.circleMessage = 'You have no Circles owned by you';
        }
      },
      error => {
        this.circleMessage = error.message;
      }
    );
  }

 addGroup(){
   console.log(this.circle);
   this.circle.createdBy = this.authService.getActiverUser();
   this.circle.circleId =  this.authService.getActiverUser() + Math.floor(Math.random() * 1000);
    this.circleService.addCircle(this.circle).subscribe(
      data => {
        console.log(data);
        this.circleMessage = 'Circle created Successfully!';
        location.reload();
      },
      error => {
        console.log(error);
        this.circleMessage = error.message;
      }
    );
this.circleService.joinCircle(this.circle,this.userId).subscribe();
 }

 deleteCircle(group){
  console.log(group);
  this.circleService.deleteCircle(group).subscribe(
    data => {
      let index = this.newCirclelist.indexOf(group);
        this.newCirclelist.splice(index, 1);
    },
    error => {
      console.log(error);
    }
  );
  this.circleService.leaveCircle(group,this.userId).subscribe(
    data=>{
      let index=this.otherCircles.indexOf(data);
      this.otherCircles.splice(index,1);
      this.otherService.shareDataSubject.next(this.otherCircles);
    }
  );
 }

 sendCircle(group){
   console.log(group);
   this.dataService.sendDataToOtherComponent(group);
 }
}
