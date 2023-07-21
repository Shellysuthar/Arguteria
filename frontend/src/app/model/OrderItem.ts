export default interface OrderItem {
  id?: string;
  name: string;
  description?: string;
  price: number;
  quantity: number;
  total?: number;
}

