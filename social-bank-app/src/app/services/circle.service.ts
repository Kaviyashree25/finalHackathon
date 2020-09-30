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
  public url1=`http://localhost:8765/circle-service/api/v1/`;

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

  deleteCircle(group: Circle) {
    return this.httpClient.delete<any>(`${this.circleUrl}/${group.createdBy}/${group.circleId}`);
  }

  updateCircle(group: Circle) {
    return this.httpClient.put<any>(`${this.circleUrl}/${group.createdBy}/${group.circleId}`, group);
  }

  joinCircle(circle:Circle,userId:string){
    return this.httpClient.post<any>(this.url1+`circle/${userId}`,circle);
  }
  leaveCircle(circle:Circle,userId:string){
    return this.httpClient.get<any>(this.url1+`circle/${circle.circleId}/${userId}`);
  }
  sendReq(circle:Circle,userId:string){
    return this.httpClient.post<any>(this.url1+`circle/SendRequest/${userId}`,circle);
  }
  acceptReq(circle:Circle,userId:string){
   return this.httpClient.post<any>(this.url1+`circle/acceptRequest/${userId}`,circle);
  }
  rejectReq(circle:Circle,userId:string){
    return this.httpClient.post<any>(this.url1+`circle/rejectRequest/${userId}`,circle);
  }
  getReq(userId){
    return this.httpClient.get<Circle[]>(this.url1+`circleRequests/${userId}`);
  }
  getCirclesByUserId(userId){
    return this.httpClient.get<Circle[]>(this.url1+`circles/${userId}`);
  }
}
