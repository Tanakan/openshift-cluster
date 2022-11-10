import { Menu } from "./Menu";

export type LunchHistory = {
	id: number;
    createdAt: Date;
	employee: String;
    menuId: number;
    price : number;
    menu : Menu;
}