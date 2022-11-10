import { UserProfile } from "../models/UserProfile";
import axios, { AxiosRequestConfig } from "axios";

export default class UserProfileService{
    private accessToken:string;

    private axiosInstance;
    
    constructor(accessToken:string){
        this.accessToken = accessToken;
        this.axiosInstance =axios.create({
            baseURL: 'http://localhost:8085',
            timeout: 1000,
            headers: { 'Content-Type': 'application/json' ,'Authorization': `Bearer  ${this.accessToken}`}
        });
    }
    public async getUserProfile(id: string): Promise<UserProfile> {
        const response = await this.axiosInstance.get(id);
        return response.data
    }
}