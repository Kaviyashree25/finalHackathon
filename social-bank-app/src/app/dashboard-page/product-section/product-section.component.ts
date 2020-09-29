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
  allProducts:Product[];
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
    );
    this.prodService.getAllProduct().subscribe(
      data=>{
        this.allProducts=data;
      },
      error=>{
        console.error();  
      }
    );
  }
  unfollow(product){
    this.prodService.unfollow(product.productId,this.user).subscribe(
      data=>{
        console.log(data);
        let index=this.products.indexOf(data);
        this.products.splice(index,1);
        this.prodService.subject1.next(this.products);
      },
      error=>{
        console.error(); 
      }
    );
  }
  follow(product){
    console.log(this.user);
    console.log(product);
    this.prodService.follow(product,this.user).subscribe(
      data=>{
        this.products.push(data);
        this.prodService.subject.next(this.products);
      },
      error=>{
        console.error();
        
      }
    );
  }

}
