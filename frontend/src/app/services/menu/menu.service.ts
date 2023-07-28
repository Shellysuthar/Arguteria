import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MenuService {
  private baseUrl = 'http://localhost:8080/api/v1/product';
  constructor(private http: HttpClient) {}

  getProducts(): Observable<any> {
    const token = localStorage.getItem('token');
    return this.http.get(`${this.baseUrl}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  addProduct(product: any): Observable<any> {
    const token = localStorage.getItem('token');
    return this.http.post(`${this.baseUrl}`, product, {
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json',
        'Accept': 'text/plain' // Set Accept header to receive plain text response
      },
      responseType: 'text' 
    });
  }

  updateProduct(product: any): Observable<any> {
    const token = localStorage.getItem('token');
    return this.http.post(`${this.baseUrl}/${product.id}`, product, {
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json',
        'Accept': 'text/plain' // Set Accept header to receive plain text response
      },
      responseType: 'text'
    });
  }

  deleteProduct(id: number): Observable<any> {
    const token = localStorage.getItem('token');
    return this.http.delete(`${this.baseUrl}/delete/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }
}
