import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Circle } from '../model/circle';
import { AuthServiceService } from './auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class CircleService {

  public circles: Circle[] = [];
  public subject = new BehaviorSubject<Array<Circle>>(this.circles);
  public circle: Circle = new Circle();
  public circleUrl = 'http://localhost:8765/bank-circle-service/api/v1/bank/circles';
  
  constructor(private httpClient: HttpClient, private authService: AuthServiceService) { }

  getAllCircles(): Observable<any> {
    return this.httpClient.get<any>(this.circleUrl);
  }

  addCircle(circle: Circle) {
    return this.httpClient.post<any>(this.circleUrl, circle);
  }

  getCirclebyCircleId(circleId) {
    return this.httpClient.get<any>(`${this.circleUrl}/${circleId}`);
  }
}
