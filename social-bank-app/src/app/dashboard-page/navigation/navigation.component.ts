import { Component, OnInit } from '@angular/core';
import { Circle } from 'src/app/model/circle';
import { CircleService } from 'src/app/services/circle.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  circle : Circle;
  userId: string;
  constructor(private circleService: CircleService) { 
    this.circle = new Circle();
  }

  ngOnInit() {
  }

  addGroup(){
    console.log(this.circle);
    
    //this.circleService.addCircle(this.circle, this.userId);
  }

  openCity(evt, cityName) {
    // var i, tabcontent, tablinks;
    // tabcontent = document.getElementsByClassName("tabcontent");
    // for (i = 0; i < tabcontent.length; i++) {
    //   tabcontent[i].style.display = "none";
    // }
    // tablinks = document.getElementsByClassName("tablinks");
    // for (i = 0; i < tablinks.length; i++) {
    //   tablinks[i].className = tablinks[i].className.replace(" active", "");
    // }
    // document.getElementById(cityName).style.display = "block";
    // evt.currentTarget.className += " active";
  }
  

}
