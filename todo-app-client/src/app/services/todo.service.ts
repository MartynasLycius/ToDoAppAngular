import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToDo } from '../ToDo';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  private api = 'http://localhost:8080/api/todos';

  constructor(private http: HttpClient) {}

  getAllToDos(): Observable<ToDo[]> {
    return this.http.get<ToDo[]>(this.api);
  }

  createToDo(toDo: ToDo): Observable<ToDo> {
    return this.http.post<ToDo>(this.api, toDo);
  }

  getToDo(id: number): Observable<ToDo> {
    return this.http.get<ToDo>(`${this.api}/${id}`);
  }

  updateToDo(toDo: ToDo): Observable<ToDo> {
    return this.http.put<ToDo>(`${this.api}/${toDo.id}`, toDo);
  }

  deleteToDo(toDo: ToDo): Observable<any> {
    return this.http.delete(`${this.api}/${toDo.id}`);
  }
}
