export default interface Bill {
  id?:number,
  uuid?: string,
  email: string;
  firstName: string;
  lastName: string;
  productDetail: string; //previously Order[] = [];
  totalAmount: number;
  status?: string;
}
