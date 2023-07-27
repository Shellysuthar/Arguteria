import { Component ,Input ,Output, EventEmitter } from '@angular/core';
import OrderItem from 'src/app/model/OrderItem';
import { ToastrService } from 'ngx-toastr';
import { OrderService } from 'src/app/services/order/order.service';
import Bill from 'src/app/model/Bill';
import { SharedDataService } from 'src/app/services/sharedData/shared-data.service';
import { HttpErrorResponse } from '@angular/common/http';
import User from 'src/app/model/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {
  @Input() cartItems!: OrderItem[] ;
  @Output() productAdded = new EventEmitter();
  userDetails!:User;

  constructor(    
    private toastr: ToastrService, 
    private sharedDataService: SharedDataService,
    private orderService: OrderService,
    private router : Router,
  ) {}

  ngOnInit(): void {
    this.sharedDataService.userDetailsObservable.subscribe((userDetails) => {
      this.userDetails = userDetails;
    });
  }

  decreaseQuantity(index: number) {
    if (this.cartItems[index].quantity > 1) {
      this.cartItems[index].quantity = (
       this.cartItems[index].quantity - 1
      );
      this.cartItems[index].total = (
        this.cartItems[index].price * this.cartItems[index].quantity
      );
    }
  }

  increaseQuantity(index: number) {
    this.productAdded.emit( this.cartItems[index] );
    this.cartItems[index].total = (
      this.cartItems[index].price * this.cartItems[index].quantity
    );
  }

  removeItem(index: number) {
    this.cartItems.splice(index, 1);
  }

  calculateSubtotal(): number {
    return this.cartItems.reduce((acc: any, prod: OrderItem ) => acc+= (prod.price * prod.quantity) ,0)
  }

  placeOrder() {
    if (this.cartItems.length === 0) {
      this.toastr.warning(
        'You did not add any food item',
        'Please add some food options'
      );
    } else {
      const orders: Bill = {
        // createdAt: new Date().toDateString(),
        email: this.userDetails.email.toString(),
        firstName: this.userDetails.firstName.toString(),
        lastName: this.userDetails.lastName.toString(),
        productDetail: JSON.stringify(this.cartItems),
        totalAmount: this.calculateSubtotal(),
      };
      console.log(orders);
      this.orderService.addBill(orders).subscribe(
        (res: any) => {
          this.toastr.success('Your order is placed!', 'Order successful');
          // console.log(res);
        },
        (error: HttpErrorResponse) => {
          this.toastr.error('Error in placing order', 'Order Unsuccessful');
          this.router.navigate(['/orders']);
          console.log(error.message);
        }
      );
    }
  }


}
