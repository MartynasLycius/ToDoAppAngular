import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptorService implements HttpInterceptor {

  constructor(private toast: ToastrService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          this.handleError(error);
          return throwError(error);
        })
      );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 400) {
      this.toast.error(error.message, 'Bad Request');
    } else if (error.status === 401) {
      this.toast.error('', 'Token Expired');
    } else if (error.status === 417) {
      this.toast.error(error.message, 'Request error');
    } else if (error.status >= 500) {
      this.toast.error('Please contact with support team', 'Data fetching error');
    } else if (error.status >= 412) {
      this.toast.error(error?.message);
    }
  }
}
