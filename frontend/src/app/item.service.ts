import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Item } from './item';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private itemBaseApi = "http://localhost:9090/api/items";

  constructor(private httpClient: HttpClient) { }

  getItems(): Observable<Item[]>{
    return this.httpClient.get<Item[]>(`${this.itemBaseApi}`);    
  }

  saveItem(item: Item): Observable<Object>{
    return this.httpClient.post(`${this.itemBaseApi}`, item);    
  }

  getItem(id: number): Observable<Item>{
    return this.httpClient.get<Item>(`${this.itemBaseApi}/${id}`);    
  }

  updateItem(id: number, item: Item): Observable<Object>{
    return this.httpClient.put(`${this.itemBaseApi}/${id}`, item);    
  }

  deleteItem(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.itemBaseApi}/${id}`);    
  }
}
