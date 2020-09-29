import { Component, OnInit } from '@angular/core';
import { Circle } from 'src/app/model/circle';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { CircleService } from 'src/app/services/circle.service';

@Component({
  selector: 'app-circle-section',
  templateUrl: './circle-section.component.html',
  styleUrls: ['./circle-section.component.css']
})
export class CircleSectionComponent implements OnInit {

  public circle: Circle;
  public noOfMembers: number;
  circleMessage: string;

  constructor(private circleService: CircleService, private authService: AuthServiceService) {
    this.circle = new Circle();
   }

  ngOnInit() {
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
    )
   
 }

}
