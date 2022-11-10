export type Payment = {
	id: Number;
    createdAt: Date;
	menuId: Number;
	employee: String;
	price : Number;
}

export type RequestPayment = {
    menuId : Number
}