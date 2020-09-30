import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class RouterService {
  
  routeToHome() {
    this.router.navigate(['/home']);
  }
  routeTobankLogin(){
    this.router.navigate([`/bankLogin`]);
  }

  routeToMyGroup(){
    this.router.navigate(['/dashboard/circles'])
  }
  routeToMyPost(){
    this.router.navigate(['/dashboard/posts'])
  }
  routeToRequests(){
    this.router.navigate(['/dashboard/circleRequest']);
  }
  constructor(private router: Router, private location: Location) { }

  routeToDashboard() {
    this.router.navigate(['/dashboard']);
  }

  routeToLogin() {
    this.router.navigate(['/login']);
  }

  routeBack() {
    this.location.back();
  }
}
