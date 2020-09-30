import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Circle } from '../model/circle';

@Injectable({
  providedIn: 'root'
})
export class CircledataService {

  constructor() { }
  circleList: Circle[] = [];
  public shareDataSubject = new BehaviorSubject<any>(this.circleList);
  // shareDataSubject : BehaviorSubject = new BehaviorSubject<any>(); //Decalring new RxJs Subject 
  sendDataToOtherComponent(somedata) {
    console.log('hi');
    
    this.shareDataSubject.next(somedata);

}
}
