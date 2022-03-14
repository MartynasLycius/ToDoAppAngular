import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TodoAddComponent } from './features/todo-add/todo-add.component';
import { TodoListComponent } from './features/todo-list/todo-list.component';

const routes: Routes = [
  { path: '', component: TodoListComponent },
  { path: 'create', component: TodoAddComponent },
  { path: 'change/:id', component: TodoAddComponent },
  { path: 'list', component: TodoListComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
