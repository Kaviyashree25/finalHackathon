import { Component, OnInit } from '@angular/core';
import { Circle } from 'src/app/model/circle';
import { CircleService } from 'src/app/services/circle.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-circle-profile',
  templateUrl: './circle-profile.component.html',
  styleUrls: ['./circle-profile.component.css']
})
export class CircleProfileComponent implements OnInit {

  circle: Circle;
  constructor(private dataService: DataService, private circleService: CircleService) { 
    this.circle = new Circle();
    this.dataService.shareDataSubject.subscribe(
      data => {
        console.log(data);
        this.circle = data;
        console.log(this.circle.circleId);
        
        // this.circleService.getCirclebyCircleId(this.circle.circleId).subscribe(
        //   data => {
        //     this.circle = data;
        //     console.log(this.circle);
            
        //   }
        // );
      }
    );
    console.log(this.circle);
    
  }

  ngOnInit() {
   
  }

  openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
  }

}
