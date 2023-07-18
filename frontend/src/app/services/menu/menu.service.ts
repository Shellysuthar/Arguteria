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
      },
    });
  }

  updateProduct(product: any): Observable<any> {
    const token = localStorage.getItem('token');
    return this.http.post(`${this.baseUrl}`, product, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
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
