import { Component, OnInit } from '@angular/core';
import { Circle } from 'src/app/model/circle';
import { CircleService } from 'src/app/services/circle.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  circle : Circle;
  userId: string;
  constructor(private circleService: CircleService) { 
    this.circle = new Circle();
  }

  ngOnInit() {
  }

  addGroup(){
    this.circleService.addCircle(this.circle, this.userId);
  }

}
