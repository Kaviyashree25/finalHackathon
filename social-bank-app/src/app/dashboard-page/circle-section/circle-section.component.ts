import { Component, OnInit } from '@angular/core';
import { Circle } from 'src/app/model/circle';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { CircleService } from 'src/app/services/circle.service';
import { DataService } from 'src/app/services/data.service';

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

  constructor(private circleService: CircleService, private authService: AuthServiceService, private dataService: DataService) {
    this.circle = new Circle();
    this.oldCircleList = [];
    this.newCirclelist = [];
    this.userId = this.authService.getActiverUser();
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
        this.circleMessage = 'Circle created Successfully!'
      },
      error => {
        console.log(error);
        this.circleMessage = error.message;
      }
    );
 }

 deleteCircle(group){
  console.log(group);
  this.circleService.deleteCircle(group).subscribe(
    data => {
      console.log(data);
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

}
