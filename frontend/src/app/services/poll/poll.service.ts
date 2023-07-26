import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Poll from 'src/app/model/Poll';

@Injectable({
  providedIn: 'root'
})
export class PollService {

  private baseUrl = 'http://localhost:8080/api/v1/poll';
  constructor(private http: HttpClient) {}
 

  addPoll(poll:Poll): Observable<any> {
    const token = localStorage.getItem('token');
    return this.http.post(`${this.baseUrl}`, poll, {
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json',
        'Accept': 'text/plain' // Set Accept header to receive plain text response
      },
      responseType: 'text' 
    });
  }

  getPolls(): Observable<any> {
    const token = localStorage.getItem('token');
    return this.http.get(`${this.baseUrl}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  submitVote(pollId:number, optionId:number): Observable<any> {
    const token = localStorage.getItem('token');
    console.log("Submit vote")
    return this.http.post(`${this.baseUrl}/${pollId}/vote/${optionId}`,{} , {
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json',
        'Accept': 'text/plain' // Set Accept header to receive plain text response
      },
      responseType: 'text' 
    });
  }
}

