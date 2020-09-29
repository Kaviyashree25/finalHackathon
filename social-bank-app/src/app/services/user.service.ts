import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { UserAuth } from '../model/user-auth';
import { AuthServiceService } from './auth-service.service';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  public userUrl='http://localhost:8765/user-service/api/v1/user';
  public userAuthUrl='http://localhost:8765/user-auth//api/v1/auth/register';

  constructor(private httpClient: HttpClient, private authService:AuthServiceService) { }

  getUser(){
    return this.httpClient.get(this.userUrl+`/${this.authService.getActiverUser()}`);
  }

  getAuthUser(){
    return this.httpClient.get(`http://localhost:8765/user-auth/api/v1/auth/user/${this.authService.getActiverUser()}`);
  }

  addUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.userUrl, user);
  }

  addUserToAuth(user: UserAuth): Observable<User> {
    return this.httpClient.post<UserAuth>(this.userAuthUrl, user);
  }

  updatePassword(userAuth: UserAuth){
    return this.httpClient.put<any>(`http://localhost:8765/user-auth//api/v1/auth/user/changepassword/${this.authService.getActiverUser()}`, userAuth);
  }

  deleteUser(userId: string): Observable<any> {
    return this.httpClient.delete(`${this.userUrl}/${userId}`);
  }

  updateUser(user: User): Observable<User> {
    return this.httpClient.put<User>(`${this.userUrl}/${this.authService.getActiverUser()}`, user);
  }
}
