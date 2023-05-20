import {Injectable} from '@angular/core';
import {NgxUiLoaderService} from "ngx-ui-loader";
import {HttpClient} from "@angular/common/http";
import {ApiEndpoints} from "../../shared/commons/ApiEndPoint";
import {Observable} from "rxjs";
import {ITodoList} from "../models/itodo-list";
import {ItodoItem} from "../models/itodo-item";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {DatePipe} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class TodoServiceService {

  constructor(private httpClient: HttpClient, private apiEndPoint: ApiEndpoints, private fb: FormBuilder, private datePipe: DatePipe) {
  }

  getToDoList(page: number, size: number, query: string): Observable<ITodoList> {
    return this.httpClient.get<ITodoList>(this.apiEndPoint.TODO.FETCH_TODO_LIST + `?page=${page}&size=${size}&query=${query}`);
  }

  deleteItem(id: number): Observable<String> {
    return this.httpClient.delete<String>(this.apiEndPoint.TODO.DELETE_TODO_ITEM(id));
  }

  getDetails(id: string): Observable<ItodoItem> {
    return this.httpClient.get<ItodoItem>(this.apiEndPoint.TODO.FETCH_ITEM_DETAILS(Number(id)))
  }


  updateItem(value: any): Observable<ItodoItem> {
    return this.httpClient.put<ItodoItem>(this.apiEndPoint.TODO.UPDATE_DETAILS(value.id), value);
  }

  createItem(value: any): Observable<ItodoItem> {
    return this.httpClient.post<ItodoItem>(this.apiEndPoint.TODO.CREATE_TODO_ITEM, value);

  }

  populateForm(item: ItodoItem) {
    return this.fb.group({
      id: new FormControl(item.id),
      name: new FormControl(item.name, [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
      description: new FormControl(item.description),
      priority: new FormControl(item.priority),
      status: new FormControl(item.status, [Validators.required]),
      tags: new FormControl(item.tags),
      date: new FormControl(this.datePipe.transform(item.date, 'yyyy-MM-dd')),
    });
  }

  getTodoForm() {
    return this.fb.group({
      id: new FormControl(null),
      name: new FormControl(null, [Validators.required, Validators.minLength(3), Validators.maxLength(255)]),
      description: new FormControl(null),
      priority: new FormControl(null),
      status: new FormControl(null, [Validators.required]),
      tags: new FormControl(null),
      date: new FormControl(null),

    });
  }


}
