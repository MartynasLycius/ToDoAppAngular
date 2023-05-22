import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToDo } from 'src/app/ToDo';
import { TodoService } from 'src/app/services/todo.service';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent {
  toDos: ToDo[] = [];
  newToDoName: string = 'No Title';
  selectedToDo: ToDo = {
    name: '', description: '', date: new Date
  };
  
  constructor(private toDoService: TodoService,
    private router: Router) { }
  
  ngOnInit() {
    this.getToDos();
  }

  getToDos(): void {
    this.toDoService.getAllToDos().subscribe(todos => this.toDos = todos);
  }

  updateToDo(toDo: ToDo): void {
    this.toDos = this.toDos.filter(t => t !== toDo);
    this.toDoService.updateToDo(toDo).subscribe();
    this.router.navigate(['edit', toDo.id]);
  }

  deleteToDo(toDo: ToDo): void {
    this.toDos = this.toDos.filter(t => t !== toDo);
    this.toDoService.deleteToDo(toDo).subscribe();
  }

}
