import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { ProductServiceService } from 'src/app/services/product-service.service';

@Component({
  selector: 'app-product-section',
  templateUrl: './product-section.component.html',
  styleUrls: ['./product-section.component.css']
})
export class ProductSectionComponent implements OnInit {
  products:Product[];
  user:string;
  constructor(private prodService :ProductServiceService,private authService:AuthServiceService) { 
    this.user=this.authService.getActiverUser();
    this.prodService.getAllProductByUser(this.user);
  }

  ngOnInit() {
    this.prodService.subject1.subscribe(
      data=>{
        this.products=data;
      },
      error=>{
        console.error();
        
      }
    )
  }

}
