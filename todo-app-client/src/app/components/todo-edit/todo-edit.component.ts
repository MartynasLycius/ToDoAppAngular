import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToDo } from 'src/app/ToDo';
import { TodoService } from 'src/app/services/todo.service';

@Component({
  selector: 'app-todo-edit',
  templateUrl: './todo-edit.component.html',
  styleUrls: ['./todo-edit.component.css']
})
export class TodoEditComponent {
  id!: number;
  toDo!: ToDo;

  constructor(private route: ActivatedRoute,private router: Router,
    private todoService: TodoService) { }

  ngOnInit() {
    this.toDo = new ToDo();

    this.id = this.route.snapshot.params['id'];
    
    this.todoService.getToDo(this.id)
      .subscribe(data => {
        console.log(data)
        this.toDo = data;
      });
  }

  updateTodo() {
    this.todoService.updateToDo(this.toDo)
      .subscribe(data => {
        console.log(data);
        this.toDo = new ToDo();
        this.gotoList();
      });
  }

  onSubmit() {
    this.updateTodo();    
  }

  gotoList() {
    this.router.navigate(['/todos']);
  }
}
