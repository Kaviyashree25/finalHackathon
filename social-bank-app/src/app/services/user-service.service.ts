import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthServiceService } from './auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  public userUrl='http://localhost:8765/user-service/api/v1/user/';

  constructor(private httpClient: HttpClient, private authService:AuthServiceService) { }

  getUser(){
    return this.httpClient.get(this.userUrl+`/${this.authService.getActiverUser()}`);
  }


}
