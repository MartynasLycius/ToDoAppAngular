import {environment} from "../../../environments/environment";
import {Injectable} from "@angular/core";

@Injectable()
export class ApiEndpoints {

  static API_VERSION_1 = '/api/v1';

  TODO = {
    FETCH_TODO_LIST: environment.base_url + ApiEndpoints.API_VERSION_1 + '/todos',
    CREATE_TODO_ITEM: environment.base_url + ApiEndpoints.API_VERSION_1 + '/todos/create',
    FETCH_ITEM_DETAILS(id: number) {
      return environment.base_url + ApiEndpoints.API_VERSION_1 + '/todos/' + id;
    },
    UPDATE_DETAILS(id: number) {
      return environment.base_url + ApiEndpoints.API_VERSION_1 + `/todos/${id}/update`;
    },
    DELETE_TODO_ITEM(id: number) {
      return environment.base_url + ApiEndpoints.API_VERSION_1 + `/todos/${id}/delete`;
    },

  };
}
