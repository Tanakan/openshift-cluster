import axios, { AxiosRequestConfig } from "axios";
import { LunchHistory } from "../models/LunchHistory";
import { useAxios } from './UseAxios';

export default class LunchHistoryService {

    URL:string = 'http://localhost:8082'

    public async listLunchHistories(): Promise<LunchHistory[]> {
        const response = await useAxios(this.URL).current!.get("/");        
        return response.data
    }
}