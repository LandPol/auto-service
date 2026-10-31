import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Car} from '../models/car.model';
import {CarCollection} from '../models/car-collection.model';

@Injectable({
  providedIn: "root"
})

export class CarService {
  private apiUrl = "http://localhost:8080/api/clients";

  constructor(private http: HttpClient) {

  }

  getCarsByClientId(clientId: string): Observable<CarCollection[]> {
    return this.http.get<CarCollection[]>(`${this.apiUrl}/${clientId}/cars`);
  }

  getCarById(clientId: string, carId: string): Observable<Car> {
    return this.http.get<Car>(`${this.apiUrl}/${clientId}/cars/${carId}`);
  }

  deleteCarById(clientId: string, carId: string): Observable<any>{
    return this.http.delete<Car>(`${this.apiUrl}/${clientId}/cars/${carId}`);
  }

  createCar(clientId: string, car: {model: string, type: string, productionYear: number, clientId: string}): Observable<Car> {
    return this.http.post<Car>(`${this.apiUrl}/${clientId}/cars`, car);
  }

  updateCarById(clientId: string, carId: string, car: {model: string, type: string, productionYear: number, clientId: string}): Observable<Car> {
    return this.http.put<Car>(`${this.apiUrl}/${clientId}/cars/${carId}`, car);
  }
}
