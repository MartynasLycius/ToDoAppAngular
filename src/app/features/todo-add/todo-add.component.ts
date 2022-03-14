import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Todo } from 'src/app/model/todo.model';
import { apiConfig } from 'src/app/providers/app.config';

@Component({
  selector: 'app-todo-add',
  templateUrl: './todo-add.component.html'
})
export class TodoAddComponent implements OnInit {
  public id: string = '';
  public todo = new Todo();
  name: string = '';
  constructor(private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    if (this.id) {
      this.getTodo(this.id);
    }
  }

  getTodo(id: string) {
    this.http.get<any>(apiConfig.apiUrl + '/todo/' + id).subscribe(data => {
      this.todo = new Todo(data);
    })
  }

  createTodo() {
    this.http.post<any>(apiConfig.apiUrl + '/todo', new Todo(this.todo)).subscribe(data => {
      this.router.navigateByUrl('/');
    })
  }

  changeTodo() {
    this.http.put<any>(apiConfig.apiUrl + '/todo/' + this.todo.id, this.todo).subscribe(data => {
      this.router.navigateByUrl('/');
    })
  }



}
