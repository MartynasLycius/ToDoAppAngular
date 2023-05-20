import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ToDoListComponent} from './todo/components/toDoListComponent/toDoList.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgxUiLoaderModule} from "ngx-ui-loader";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ApiEndpoints} from "./shared/commons/ApiEndPoint";
import {JwtInterceptorServiceService} from "./shared/services/jwt-interceptor-service.service";
import {ErrorInterceptorService} from "./shared/services/error-interceptor.service";
import {ToastrModule, ToastrService} from "ngx-toastr";
import { EditTodoItemComponent } from './todo/components/edit-todo-item/edit-todo-item.component';
import {MatSelectModule} from "@angular/material/select";
import {MatFormFieldModule} from "@angular/material/form-field";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {DatePipe} from "@angular/common";
import {MatPaginatorModule} from "@angular/material/paginator";

@NgModule({
  declarations: [
    AppComponent,
    ToDoListComponent,
    EditTodoItemComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    NgxUiLoaderModule,
    HttpClientModule,
    ToastrModule.forRoot({
      closeButton: true,
      progressBar: true,
      progressAnimation: 'increasing',
    }),
    ReactiveFormsModule,
    MatSelectModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    MatPaginatorModule
  ],
  providers: [
    ToastrService,
    ApiEndpoints,
    DatePipe,
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorServiceService, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptorService, multi: true},
  ],

  bootstrap: [AppComponent]
})
export class AppModule {
}
