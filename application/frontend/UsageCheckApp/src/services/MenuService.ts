import { Menu } from "../models/Menu";
import axios from "axios"

export default class MenuService {
    instance = axios.create({
        baseURL: 'hhttps://i-launchhistory-food.apps.test.example.com/launch_history',
        timeout: 1000,
        headers: { 'Content-Type': 'application/json' }
    });

    public async listMenues(accessToken: String) {
        return await this.instance.get("/launch_his", {
            headers: {
                Authorization: "Bearer " + accessToken
            }
        });
    }
}