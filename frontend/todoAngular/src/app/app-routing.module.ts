import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ToDoListComponent} from "./todo/components/toDoListComponent/toDoList.component";
import {EditTodoItemComponent} from "./todo/components/edit-todo-item/edit-todo-item.component";

const routes: Routes = [
  {path: '', component: ToDoListComponent},
  {path: 'todos', component: EditTodoItemComponent},
  {path: 'todos/:id', component: EditTodoItemComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
