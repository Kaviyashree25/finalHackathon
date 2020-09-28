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
  constructor(private httpClient: HttpClient, private authService: AuthServiceService) { }

  // getAllCircles(): Observable<any> {
  //   return this.httpClient.get<any>('http://localhost:8765/bank-circle-service/api/v1/bank/circles');
  // }

  // addCircle(circle) {
  //   return this.httpClient.post<any>('http://localhost:8765/bank-circle-service/api/v1/bank/circles', circle)
  //   , {
  //     headers: new HttpHeaders().set('Authorization', `Bearer ${this.authService.getBearerToken()}`)
  //   });
  // }

  addCircle(circle, userId) {
    return this.httpClient.post<any>('http://localhost:8765/circle-service/api/v1/circle', circle, userId)
      // , {
      // headers: new HttpHeaders().set('Authorization', `Bearer ${this.authService.getBearerToken()}`)
    // });
  }
  getFavourite() {
    return this.httpClient.get<any>('http://localhost:8765/favourite-service/api/v1/favourites')
    // , {
    //   headers: new HttpHeaders().set('Authorization', `Bearer ${this.authService.getBearerToken()}`)
    // });
  }

  deleteCircle(gif) {
    return this.httpClient.delete<any>(`http://localhost:8765/favourite-service/api/v1/favourite/${gif.id}`)
    // , {
    //   headers: new HttpHeaders().set('Authorization', `Bearer ${this.authService.getBearerToken()}`)
    // });
  }
}
