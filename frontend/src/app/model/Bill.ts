export default interface Bill {
  createdBy: string;
  email: string;
  firstName: string;
  lastName: string;
  productDetail: string; //previously Order[] = [];
  totalAmount: number;
}
