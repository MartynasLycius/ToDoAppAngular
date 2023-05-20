import {Component, OnDestroy, OnInit} from '@angular/core';
import {ITodoList} from "../../models/itodo-list";
import {TodoServiceService} from "../../services/todo-service.service";
import {NgxUiLoaderService} from "ngx-ui-loader";
import {Router} from "@angular/router";
import {PageEvent} from "@angular/material/paginator";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-todo',
  templateUrl: './toDoList.component.html',
  styleUrls: ['./toDoList.component.scss']
})
export class ToDoListComponent implements OnInit, OnDestroy {
  search_key: string = '';
  isLoading: boolean = false;
  isFirst: boolean = true;
  isLast: boolean = false;
  todoList!: ITodoList;
  page: number = 0;
  size: number = 10;
  private listSubscription: Subscription | undefined;
  private deleteSubscription: Subscription | undefined;

  constructor(private todoService: TodoServiceService, private ngxService: NgxUiLoaderService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getTodoList(this.page, this.size, this.search_key)
  }

  ngOnDestroy() {
    this.listSubscription?.unsubscribe();
    this.deleteSubscription?.unsubscribe();
  }


  getTodoList(page: number = 0, size: number, search_key: string = ''): void {
    this.ngxService.startLoader("loader-01");
    this.listSubscription = this.todoService.getToDoList(page, size, search_key).subscribe((list: ITodoList) => {
      this.todoList = list
      this.isFirst = list.first;
      this.isLast = list.last;
      this.ngxService.stopLoader("loader-01");
    }, error => {
      this.ngxService.stopLoader("loader-01");
    })
  }

  deleteItem(index: number, id: number) {
    if (confirm("Are you sure").valueOf()) {
      this.deleteSubscription = this.todoService.deleteItem(id).subscribe(item => {
        this.todoList.content.splice(index, 1);
      }, error => {
        if (error.status === 200) {
          this.todoList.content.splice(index, 1);
        }
      })
    }
  }

  updateItem(id: number) {
    this.router.navigate([`/todos/${id}`]);

  }

  onPaginateChange(event: PageEvent) {
    this.getTodoList(event.pageIndex, event.pageSize)
  }
}
