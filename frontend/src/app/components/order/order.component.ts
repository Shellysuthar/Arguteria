import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import OrderItem from 'src/app/model/OrderItem';
import Bill from 'src/app/model/Bill';
import { AuthService } from 'src/app/services/auth/auth.service';
import { MenuService } from 'src/app/services/menu/menu.service';
import { OrderService } from 'src/app/services/order/order.service';
import { SharedDataService } from 'src/app/services/sharedData/shared-data.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent implements OnInit {
  userDetails!: any;
  newOrderForm!: FormGroup;
  order: OrderItem[] = [];
  selectedOrderType: string = '';
  selectedPaymentMethod: string = '';
  items!: any;

  constructor(
    private fb: FormBuilder,
    private toastr: ToastrService,
    private orderService: OrderService,
    private authService: AuthService,
    private sharedDataService: SharedDataService,
    private menuService: MenuService
  ) {}

  ngOnInit(): void {
    this.sharedDataService.userDetailsObservable.subscribe((userDetails) => {
      this.userDetails = userDetails;
    });
  }

  // decreaseQuantity(index: number) {
  //   if (parseInt(this.order[index].quantity) > 1) {
  //     this.order[index].quantity = (
  //       parseInt(this.order[index].quantity) - 1
  //     ).toString();
  //     this.order[index].total = (
  //       parseInt(this.order[index].price) * parseInt(this.order[index].quantity)
  //     ).toFixed(2);
  //   }
  // }

  // increaseQuantity(index: number) {
  //   this.order[index].quantity = (
  //     parseInt(this.order[index].quantity) + 1
  //   ).toString();
  //   this.order[index].total = (
  //     parseInt(this.order[index].price) * parseInt(this.order[index].quantity)
  //   ).toFixed(2);
  // }

  removeItem(index: number) {
    this.order.splice(index, 1);
  }

  addFood() {
    const currentOrder = this.newOrderForm.value;
    if (currentOrder.food != '' && currentOrder.quantity != '') {
      if (parseInt(currentOrder.quantity) < 1) {
        this.toastr.warning(
          'Number of items must be at least one',
          'Invalid quantity'
        );
      } else {
        this.newOrderForm = this.fb.group({
          food: [''],
          quantity: [''],
          orderType: [this.selectedOrderType, Validators.required],
          paymentMethod: [this.selectedPaymentMethod, Validators.required],
        });

        const foodAndPrice = currentOrder.food;
        const [food, pr] = foodAndPrice.split(' - ');
        const price = parseFloat(pr).toFixed(2);
        const quantity = currentOrder.quantity;
        const singleOrder: OrderItem = {
          name: food,
          price: parseInt(price),
          quantity: parseInt(quantity),
          total: parseInt(price) * parseInt(quantity),
        };
        this.order.push(singleOrder);
      }
    } else {
      this.toastr.warning(
        'Please choose both food option and the quantity',
        'Invalid item'
      );
    }
  }

  calculateSubtotal(): number {
    const sumTotal = this.order.reduce((acc, order) => {
      return acc + order.total;
    }, 0);
    return sumTotal;
  }

  onOrderTypeChange(event: any) {
    this.selectedOrderType = event.target.value;
  }

  onPaymentMethodChange(event: any) {
    this.selectedPaymentMethod = event.target.value;
  }

  submitOrder() {
    if (this.order.length === 0) {
      this.toastr.warning(
        'You did not add any food item',
        'Please add some food options'
      );
    } else {
      const orders: Bill = {
        createdBy: this.userDetails[0].userName.toString(),
        email: this.userDetails[0].email.toString(),
        firstName: this.userDetails[0].firstName.toString(),
        lastName: this.userDetails[0].lastName.toString(),
        productDetail: JSON.stringify(this.order),
        totalAmount: this.calculateSubtotal(),
      };
      // console.log(orders);
      this.order = [];
      this.newOrderForm = this.fb.group({
        food: [''],
        quantity: [''],
        orderType: ['', Validators.required],
        paymentMethod: ['', Validators.required],
      });
      this.orderService.addBill(orders).subscribe(
        (res: any) => {
          this.toastr.success('Your order is placed!', 'Order successful');
          // console.log(res);
        },
        (error: HttpErrorResponse) => {
          this.toastr.success('Your order is placed!', 'Order successful');
          // console.log(error.message);
        }
      );
    }
  }

  getProducts() {
    this.menuService.getProducts().subscribe(
      (res: any) => {
        // console.log(res);
        this.items = res;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }
}
