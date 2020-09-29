import { Component, OnInit } from '@angular/core';
import { Circle } from 'src/app/model/circle';
import { CircleService } from 'src/app/services/circle.service';
import { DataService } from 'src/app/services/data.service';
import { RouterService } from 'src/app/services/router.service';

@Component({
  selector: 'app-circle-update',
  templateUrl: './circle-update.component.html',
  styleUrls: ['./circle-update.component.css']
})
export class CircleUpdateComponent implements OnInit {

  circle: Circle;
  circleMessage: string;
  userId: string;
  circleId: string;
  constructor(private dataService: DataService, private circleService: CircleService, private routerService: RouterService) { 
    this.circle = new Circle();
    this.dataService.shareDataSubject.subscribe(
      data => {
        console.log(data);
        this.circle = data;
        console.log(this.circle.circleId);
        this.userId = this.circle.createdBy;
        this.circleId = this.circle.circleId;
        // this.circleService.getCirclebyCircleId(this.circle.circleId).subscribe(
        //   data => {
        //     this.circle = data;
        //     console.log(this.circle);
            
        //   }
        // );
      }
    );
  }

  ngOnInit() {
  }

  updateGroup(){
    console.log(this.circle);
    // this.circle.circleId = this.circleId;
    // this.circle.createdBy = this.userId;
    // console.log(this.circle);
    
    this.circleService.updateCircle(this.circle).subscribe(
      data => {
        this.circleMessage = 'Updated Successfully';
        this.routerService.routeToMyGroup();
      },
      error => {
        this.circleMessage = error.message;
      }
    )
  }

}
