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
