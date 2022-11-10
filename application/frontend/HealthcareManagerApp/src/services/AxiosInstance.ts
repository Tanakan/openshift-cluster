import { useKeycloak } from "@react-keycloak/web";
import axios, { AxiosRequestConfig } from "axios";


export const useAxios =() => {
    const {keycloak} = useKeycloak();
    
    const axiosInstance = axios.create({
        baseURL: 'https://i-launchhistory-food.apps.test.example.com',
        timeout: 1000,
        headers: { 'Content-Type': 'application/json' }
    });

    axiosInstance.interceptors.request.use(
        (config:AxiosRequestConfig) => {
          config.headers =  {'Authorization' : `Bearer  ${keycloak.token}` };
          return config;
        }
    );
    return axiosInstance;
}
