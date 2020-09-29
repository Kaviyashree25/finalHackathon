import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Product } from '../model/product';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {
  public products: Product[] = [];
  public userProducts: Product[] = [];
  public subject = new BehaviorSubject<any>(this.products);
  public subject1 = new BehaviorSubject<any>(this.userProducts);
  public product: Product = new Product();
  private url1= "http://localhost:8765/bank-service/api/v1/bank/products;"
  private url2 ="http://localhost:8765/product-service/api/v1/products"

  constructor(private httpClient:HttpClient){
    this.httpClient.get(this.url1).subscribe(
      data=>{
      this.subject.next(data);
      },
      error=>{
        console.error();
        
      }
    )
  }
  
  getAllProduct(){
    return this.httpClient.get<any>(this.url1);
  }
  getAllUsersForProduct(productId){
    return this.httpClient.get<User[]>(this.url2+`/user?productId=${productId}`);
  }

  addProduct(product:Product){
    return this.httpClient.post<Product>(this.url1,product);
  }
  deleteProduct(productId:string){
    return this.httpClient.delete<Product>(this.url1+`/${productId}`);
  }
  updateProduct(product:Product){
    return this.httpClient.put<Product>(this.url1+`/${product.productId}`,product);
  }
  getAllProductByUser(userId:string){
    this.httpClient.get<Product[]>(this.url2+`/${userId}`).subscribe(
      data=>{
        this.subject1.next(data);
      },
      error=>{
          console.error();
          
      }
    );
  }
}
