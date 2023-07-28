import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';

import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import OrderItem from 'src/app/model/OrderItem';
import { AuthService } from 'src/app/services/auth/auth.service';
import { MenuService } from 'src/app/services/menu/menu.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent implements OnInit {
  @ViewChild('addModal') addModal: any;
  @ViewChild('editModal') editModal: any;
  @ViewChild('deleteModal') deleteModal: any;
  
  
  cartItems:OrderItem[] = [];
  item!: any;
  deleteItem!: any;
  items!: any;
  addItemForm!: FormGroup;
  constructor(
    private menuService: MenuService,
    private authService: AuthService,
    private toastr: ToastrService,
    private fb: FormBuilder,
    private modalService: NgbModal
  ) {}
  ngOnInit(): void {
    this.addItemForm = this.fb.group({
      name: ['', Validators.required],
      price: ['', Validators.required],
      description: ['', Validators.required],
    });
    this.getProducts();
  }

  
  getRole(): boolean {
    return this.authService.getRole();
  }
  getProducts() {
    this.menuService.getProducts().subscribe(
      (res: any) => {
        //console.log(res);
        this.items = res;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  addProduct(product: any) {
    this.menuService.addProduct(product).subscribe(
      (res: any) => {
        this.getProducts();
        this.toastr.success(res);
      },
      (error: HttpErrorResponse) => {      
        console.log(error.message);
      }
    );
    this.addItemForm.reset();
    this.closeAddModal();
  }

  updateProduct(product: any, id: number) {
    product.id = id;
    const item: any = {
      id: id,
      name: product.name,
      description: product.description,
      price: product.price,
    };
    console.log(item);

    this.menuService.updateProduct(item).subscribe(
      (res: any) => {
        this.getProducts();
        this.toastr.success(res);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
    this.closeEditModal();
  }

  deleteProduct(id: number) {
    this.menuService.deleteProduct(id).subscribe(
      (res: any) => {
        this.getProducts();
      },
      (error: HttpErrorResponse) => {
        this.toastr.success(error.message);
        console.log(error.message);
      }
    );
    this.closeDeleteModal();
  }
  
  addProductToCart(product:OrderItem) {    
    const productExistInCart = this.cartItems.find(({name}) => name === product.name); // find product by name
      if (!productExistInCart) {
        this.cartItems.push({...product, quantity:1}); // enhance "porduct" opject with "num" property
        return;
      }
   productExistInCart.quantity += 1;
  }

  openAddModal() {
    this.modalService.open(this.addModal);
  }

  closeAddModal() {
    this.modalService.dismissAll();
  }

  openEditModal(item: any) {
    this.item = item;
    this.modalService.open(this.editModal);
  }

  closeEditModal() {
    this.modalService.dismissAll();
  }

  openDeleteModal(item: any) {
    this.deleteItem = item;
    this.modalService.open(this.deleteModal);
  }

  closeDeleteModal() {
    this.modalService.dismissAll();
  }
}
