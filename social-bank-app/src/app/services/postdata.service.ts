import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Post } from '../model/post';

@Injectable({
  providedIn: 'root'
})
export class PostdataService {
  postList: Post[] = [];
  public shareDataSubject = new BehaviorSubject<any>(this.postList);
  constructor() { }
}
