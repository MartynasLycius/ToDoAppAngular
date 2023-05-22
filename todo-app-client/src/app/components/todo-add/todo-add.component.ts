import { Component } from '@angular/core';
import { ToDo } from 'src/app/ToDo';
import { TodoService } from 'src/app/services/todo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-todo-add',
  templateUrl: './todo-add.component.html',
  styleUrls: ['./todo-add.component.css']
})
export class TodoAddComponent {
  toDos: ToDo[] = [];
  newToDoName: string = 'No Title';
  selectedToDo: ToDo = {
    name: '', description: '', date: new Date
  };
  
  constructor(private toDoService: TodoService,
    private router: Router) { }
  
  
  addToDo(): void {
   
    this.toDoService.createToDo(this.selectedToDo)
      .subscribe(toDo => {
        console.log("toDo=====");
        console.log(toDo);
        this.toDos.push(toDo);
        this.newToDoName = '';
        this.gotoList();
        
      });
      
  }
 
  onSubmit() {
    this.addToDo();    
  }

  gotoList() {
    this.router.navigate(['/todos']);
  }
}
