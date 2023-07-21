import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Bill from 'src/app/model/Bill';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private baseUrl = 'http://localhost:8080/api/v1/bill';
  constructor(private http: HttpClient) {}
 

  addBill(orders: Bill): Observable<any> {
    const token = localStorage.getItem('token');
    return this.http.post(`${this.baseUrl}/generateBill`, orders, {
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json',
        'Accept': 'text/plain' // Set Accept header to receive plain text response
      },
      responseType: 'text' 
    });
  }

  getAllBills(): Observable<any> {
    const token = localStorage.getItem('token');
    return this.http.get(`${this.baseUrl}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }
}
