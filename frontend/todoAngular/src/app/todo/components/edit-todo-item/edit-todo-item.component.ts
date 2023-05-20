import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {TodoServiceService} from "../../services/todo-service.service";
import {ActivatedRoute} from "@angular/router";
import {ItodoItem} from "../../models/itodo-item";
import {Location} from "@angular/common";
import {Subscription} from "rxjs";
import {NgxUiLoaderService} from "ngx-ui-loader";

@Component({
  selector: 'app-edit-todo-item',
  templateUrl: './edit-todo-item.component.html',
  styleUrls: ['./edit-todo-item.component.scss']
})
export class EditTodoItemComponent implements OnInit, OnDestroy {
  todoForm: FormGroup;
  paramId: string | null | undefined
  private detailsSubscription: Subscription | undefined;
  private createSubscription: Subscription | undefined;
  private updateSubscription: Subscription | undefined;

  constructor(private todoService: TodoServiceService, private route: ActivatedRoute, private location: Location,
              private ngxService: NgxUiLoaderService) {
    this.todoForm = this.todoService.getTodoForm();
  }

  ngOnInit() {
    this.paramId = this.route.snapshot.paramMap.get('id');
    if (this.paramId)
      this.getItemDetails(this.route.snapshot.paramMap.get('id'));
  }

  ngOnDestroy() {
    this.detailsSubscription?.unsubscribe();
    this.createSubscription?.unsubscribe();
    this.updateSubscription?.unsubscribe();
  }

  private getItemDetails(id: string | null) {
    if (id) {
      this.ngxService.startLoader("loader-02");
      this.detailsSubscription = this.todoService.getDetails(id).subscribe((item: ItodoItem) => {
        this.todoForm = this.todoService.populateForm(item);
        this.ngxService.stopLoader("loader-02");

      }, error => {
        this.ngxService.stopLoader("loader-02");
      })
    }
  }

  onSubmitForm() {
    if (this.paramId)
      this.updateItem()
    else {
      this.createTodoItem()
    }
  }

  createTodoItem() {
    this.createSubscription = this.todoService.createItem(this.todoForm.value).subscribe((item: ItodoItem) => {
      this.goBack();
    })
  }

  private updateItem() {
    this.updateSubscription = this.todoService.updateItem(this.todoForm.value).subscribe((item: ItodoItem) => {
      this.goBack();
    })
  }

  isValid() {
    return !this.todoForm.valid
  }

  goBack() {
    this.location.back();
  }
}
