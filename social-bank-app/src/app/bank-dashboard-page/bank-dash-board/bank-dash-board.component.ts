import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/model/product';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { ProductServiceService } from 'src/app/services/product-service.service';

@Component({
  selector: 'app-bank-dash-board',
  templateUrl: './bank-dash-board.component.html',
  styleUrls: ['./bank-dash-board.component.css']
})
export class BankDashBoardComponent implements OnInit {
  productId: any;
  display = false;
  displayAdd = false;
  displayDelete = false;
  productList: Product[];
  userList: String[] = [];

  constructor(private prodService: ProductServiceService, private authService: AuthServiceService) {

  }

  ngOnInit() {
    this.prodService.subject.subscribe(
      data => {
        this.productList = data;
      }
    )
  }
  find(formvalue) {
    this.productId = formvalue.productId;
    this.display = true;
    this.prodService.getAllUsersForProduct(this.productId).subscribe(
      data => {
        data.forEach(element => {
          this.userList.push(element.userId)
        });
      },
      error => {
        console.error();
      }
    );
  }
  showAdd() {
    if (this.displayAdd == true) {
      this.displayAdd = false;
    } else {
      this.displayAdd = true;
      this.displayDelete = false;
    }
  }
  showDelete() {
    if (this.displayDelete == true) {
      this.displayDelete = false;
    } else {
      this.displayDelete = true;
      this.displayAdd = false;
    }
  }
  add(formvalue) {
    this.prodService.addProduct(formvalue).subscribe(
      data => {
        this.productList.push(data);
        this.prodService.subject.next(this.productList);
      },
      error => {
        console.error();

      }
    );
    this.displayAdd = false;
    this.display = false;
  }
  delete(formvalue) {
    this.prodService.deleteProduct(formvalue.productId).subscribe(
      data => {
        let index = this.productList.indexOf(data);
        this.productList.splice(index, 1);
        this.prodService.subject.next(this.productList);
      },
      error => {
        console.error();

      }
    );
    this.displayDelete = false;
    this.display = false;
  }
  update(formvalue) {
    this.prodService.updateProduct(formvalue).subscribe(
      data => {
        let index = this.productList.indexOf(formvalue);
        this.productList.splice(index, 1);
        this.productList.push(data);
        this.prodService.subject.next(this.productList);
      },
      error => {
        console.error();
      }
    );
    this.displayAdd = false;
    this.display = false;
  }
  getProdId() {
    return this.productId;
  }

}
