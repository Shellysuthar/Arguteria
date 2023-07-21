import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import OrderItem from 'src/app/model/OrderItem';
import Bill from 'src/app/model/Bill';
import { Observable, catchError, forkJoin, map, of } from 'rxjs';
import { AuthService } from 'src/app/services/auth/auth.service';
import { OrderService } from 'src/app/services/order/order.service';
import { SharedDataService } from 'src/app/services/sharedData/shared-data.service';
import User from 'src/app/model/User';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent implements OnInit {
  userDetails!: User;
  allOrders:Bill[] = [];
  products!: any;
  setProductDetails: boolean = false;

  constructor(
    private orderService: OrderService, 
    private authService: AuthService, 
    private sharedDataService: SharedDataService,
  ) {}

  ngOnInit(): void {
    this.sharedDataService.userDetailsObservable.subscribe((userDetails) => {
      this.userDetails = userDetails;
    });
    if( this.getRole() ){
      this.getOrders().subscribe(
        orders => {
          this.allOrders = orders;
          // console.log(orders);
        },
        error => {
          console.error(error);
        }
      );
    }
    else{
      //getBillsofUser
      this.getOrders().subscribe(
        orders => {
          this.allOrders = orders;
          console.log(this.allOrders);
        },
        error => {
          console.error(error);
        }
      );
    }
  }

  getDate( bill: string ) : string {
    let billDate = parseInt(bill.split('-')[1]); 
    let date = new Date(billDate);
    let parts = date.toLocaleDateString().split("/")
    let myDate = new Date(`${parts[2]}-${parts[1]}-${parts[0]}`);
    let options: Intl.DateTimeFormatOptions = { month: 'short', day: '2-digit', year: 'numeric'};
    //console.log(myDate.toISOString());
    return `${myDate.toLocaleDateString('en-US', options )} ${  this.formatTime(date.toLocaleTimeString()) }`;
  }

  formatTime(timeString:string): string {
    const [hourString, minute] = timeString.split(":");
    const hour = +hourString % 24;
    return (hour % 12 || 12) + ":" + minute + (hour < 12 ? "AM" : "PM");
}

  getOrders(): Observable<any[]> {
    return this.orderService.getAllBills().pipe(
      map(allOrders => {
        return allOrders.map((order: any) =>  {
          const parsedProductDetail = JSON.parse(order.productDetail);
          return { ...order, productDetail: parsedProductDetail };
        });
      })
    );
  }

  setProduct(id:number){
    let order = this.allOrders.find((order) => order.id === id);
    this.products = order?.productDetail ;
    this.products.bill = order?.uuid;
    this.products.total = order?.totalAmount;
    this.setProductDetails = true;
  }

  markCompleted() : boolean{
    return true;
  }

  getRole() {
    return this.authService.getRole();
  }
}
