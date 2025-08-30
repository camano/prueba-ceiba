import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {WalletTxResponse} from "../models/WalletTxResponse";


@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private apiUrl = 'http://localhost:9001/api/History';

  constructor(private http: HttpClient) {}

  getTransactions(userId: number, limit: number = 10): Observable<WalletTxResponse[]> {
    return this.http.get<WalletTxResponse[]>(`${this.apiUrl}`);
  }
}
