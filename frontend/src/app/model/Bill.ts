export default interface Bill {
  email: string;
  firstName: string;
  lastName: string;
  productDetail: string; //previously Order[] = [];
  totalAmount: number;
}
