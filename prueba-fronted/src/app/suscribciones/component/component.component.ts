import { Component, OnInit } from '@angular/core';
import {WalletTxResponse} from "../models/WalletTxResponse";
import {TransactionService} from "../services/TransactionService";
import {SubscriptionService} from "../services/SubscriptionService";
import {OpenSubscriptionRequest, SubscriptionResponse} from "../models/ Subscription";



@Component({
  selector: 'app-component',
  templateUrl: './component.component.html',
  styleUrls: ['./component.component.css']
})
export class ComponentComponent implements OnInit {

  transactions: WalletTxResponse[] = [];
  userId = 1; // 🚨 en un caso real vendría de auth
  loading = false;

  request: OpenSubscriptionRequest = {
    fundId: 0,
    amount: 0,
    notifyChannel: 'EMAIL'
  };

  subscriptions: SubscriptionResponse[] = [];

  response?: SubscriptionResponse;
  errorMessage = '';


  constructor(private txService: TransactionService,private subscriptionService: SubscriptionService) {}

  ngOnInit(): void {
    this.loadTransactions();
  }

  loadTransactions(): void {
    this.loading = true;
    this.txService.getTransactions(this.userId, 10).subscribe({
      next: (data) => {
        this.transactions = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error cargando transacciones', err);
        this.loading = false;
      }
    });
  }
  open(): void {
    this.loading = true;
    this.errorMessage = '';
    this.response = undefined;

    this.subscriptionService.openSubscription(this.request).subscribe({
      next: (res) => {
        this.response = res;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = 'No se pudo abrir la suscripción.';
        this.loading = false;
      }
    });
  }

  onCancel(subscriptionId: number): void {
    this.loading = true;
    this.subscriptionService.cancelSubscription(subscriptionId, 'EMAIL').subscribe({
      next: (res) => {
        console.log('Cancelada:', res);
        // refrescar lista en UI
        this.subscriptions = this.subscriptions.filter(s => s.id !== subscriptionId);
        this.loading = false;
      },
      error: (err) => {
        console.error('Error cancelando', err);
        this.loading = false;
      }
    });
  }

}
