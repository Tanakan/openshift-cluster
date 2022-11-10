export type Payment = {
	id: number;
    createdAt: Date;
	menuId: number;
	employee: String;
	price : number;
}

export type RequestPayment = {
    menuId : number
}