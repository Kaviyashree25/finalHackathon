import { Component, OnInit } from '@angular/core';
import { Circle } from 'src/app/model/circle';

@Component({
  selector: 'app-circle-section',
  templateUrl: './circle-section.component.html',
  styleUrls: ['./circle-section.component.css']
})
export class CircleSectionComponent implements OnInit {

  public circle: Circle;
  public noOfMembers: number;
  constructor() {
    this.circle = new Circle();
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
