import { Component, OnInit } from '@angular/core';
import { Circle } from 'src/app/model/circle';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { CircleService } from 'src/app/services/circle.service';

@Component({
  selector: 'app-circle-explore',
  templateUrl: './circle-explore.component.html',
  styleUrls: ['./circle-explore.component.css']
})
export class CircleExploreComponent implements OnInit {

  oldCircleList: Circle[];
  newCircleList: Circle[];
  circle: Circle;
  circleMessage: string;
  userId: string;
  constructor(private circleService: CircleService, private authService: AuthServiceService) { 
    this.circle = new Circle();
    this.oldCircleList = [];
    this.newCircleList = [];
    this.userId = this.authService.getActiverUser();
  }

  ngOnInit() {
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

}
