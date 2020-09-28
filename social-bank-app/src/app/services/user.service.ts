import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private API_URL = "http://localhost:9600/api/v1/user";


  constructor(private httpClient: HttpClient) { }

  addUser(user: User): Observable<User> {
    return this.httpClient.post<User>('${this.API_URL}', user);
  }

  deleteUser(userId: string): Observable<any> {
    return this.httpClient.delete('${this.API_URL}/${userId}', {responseType:'text'});
  }

  updateUser(user: User, userId: string): Observable<User> {
    return this.httpClient.put<User>('${this.API_URL}/${userId}', user);
  }
}
