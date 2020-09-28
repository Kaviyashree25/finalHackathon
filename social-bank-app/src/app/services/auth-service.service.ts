import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Circle } from '../model/circle';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  public circle: Circle;
  public activeUser: string;
  private userAuth_url = 'http://localhost:8761/user-auth/api/v1/auth/login';
  constructor(private httpClient: HttpClient) {
    this.circle = new Circle();
  }

  public authenticateUser(user) {
    console.log(user);
    return this.httpClient.post(this.userAuth_url, user);
  }

  setActiveUser(userId) {
    this.activeUser = userId;
  }

  getActiverUser() {
    return this.activeUser;
  }

  setBearerToken(token) {
    localStorage.setItem('bearerToken', token);
  }

  getBearerToken() {
    return localStorage.getItem('bearerToken');
  }

  clearBearerToken() {
    this.activeUser = undefined;
    localStorage.clear();
  }

  isUserAuthenticated() {
    if (this.getBearerToken()) {
      return true;
    } else {
      return false;
    }
  }
}
