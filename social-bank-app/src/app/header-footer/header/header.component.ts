import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { RouterService } from 'src/app/services/router.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private authService: AuthServiceService, private routerService: RouterService) { }

  ngOnInit() {
  }

  logout(){
    this.authService.clearBearerToken();
    this.routerService.routeToHome();
  }

}
