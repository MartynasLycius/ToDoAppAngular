import {ItodoItem} from "./itodo-item";
import {IPageable} from "../../shared/models/ipageable";
import {ISort} from "../../shared/models/isort";

export interface ITodoList {
  content: ItodoItem[]
  pageable: IPageable
  totalPages: number
  totalElements: number
  last: boolean
  size: number
  number: number
  sort: ISort
  numberOfElements: number
  first: boolean
  empty: boolean
}
