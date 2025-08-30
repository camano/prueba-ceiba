// src/app/services/subscription.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {OpenSubscriptionRequest, SubscriptionResponse} from "../models/ Subscription";


@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  private apiUrl = 'http://localhost:9001/api/subscriptions';

  constructor(private http: HttpClient) {}

  openSubscription(req: OpenSubscriptionRequest): Observable<SubscriptionResponse> {
    return this.http.post<SubscriptionResponse>(this.apiUrl, req);
  }
  cancelSubscription(id: number, notifyChannel: string = 'EMAIL'): Observable<SubscriptionResponse> {
    return this.http.delete<SubscriptionResponse>(`${this.apiUrl}/${id}`);
  }
}
