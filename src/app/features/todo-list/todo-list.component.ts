import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Todo } from 'src/app/model/todo.model';
import { apiConfig } from 'src/app/providers/app.config';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html'
})
export class TodoListComponent implements OnInit {
  public todoList = new Array<Todo>();
  constructor(
    private http: HttpClient) { }

  ngOnInit(): void {
    this.getTodoList();
  }

  getTodoList() {
    this.http.get<any>(apiConfig.apiUrl + '/todo/').subscribe(data => {
      this.todoList = data;
    })
  }

  deleteTodo(id: string) {
    this.http.delete<any>(apiConfig.apiUrl + '/todo/' + id).subscribe(data => {
      this.getTodoList();
    })
  }

}
