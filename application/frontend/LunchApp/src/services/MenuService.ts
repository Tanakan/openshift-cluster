import { Menu } from "../models/Menu";
import { useAxios } from './UseAxios';

export default class MenuService {

    public async listMenues(): Promise<Menu[]> {
        const response = await useAxios('http://localhost:8081').current!.get("/");
        return response.data
    }
}