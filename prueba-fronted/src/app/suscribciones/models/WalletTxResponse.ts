
export interface WalletTxResponse {
  id: number;
  userId: number;
  fundId: number;
  type: 'OPEN' | 'CANCEL';
  amount: number;
  createdAt: string;
  subscriptionId: number;
}
