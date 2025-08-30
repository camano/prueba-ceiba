// src/app/models/subscription.model.ts
export interface OpenSubscriptionRequest {
  fundId: number;
  amount: number;
  notifyChannel: string;
}

export interface SubscriptionResponse {
  id: number;
  userId: number;
  fundId: number;
  amount: number;
  status: string;
  createdAt: string;
  cancelledAt?: string;
}
