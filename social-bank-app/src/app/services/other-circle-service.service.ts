import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Circle } from '../model/circle';

@Injectable({
  providedIn: 'root'
})
export class OtherCircleServiceService {

  constructor() { }

  circleList: Circle[] = [];
  public shareDataSubject = new BehaviorSubject<any>(this.circleList);

}
