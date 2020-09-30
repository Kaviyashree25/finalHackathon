import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Post } from '../model/post';
import { AuthServiceService } from './auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private url = "http://localhost:8765/post-service/api/v1/post"

  public posts: Post[] = [];
  public subject = new BehaviorSubject<any>(this.posts);
  public post: Post = new Post();

  constructor(private httpClient: HttpClient, private authService: AuthServiceService) { }

  addPost(post: Post) {
    return this.httpClient.post<any>(`${this.url}/${post.userId}`, post);

  }

  deletePost(post: Post) {
    return this.httpClient.delete<any>(`${this.url}/${post.userId}/${post.postId}`);
  }

  updatePost(post: Post) {
    return this.httpClient.put<any>(`${this.url}/${post.userId}/${post.postId}`, post);

  }

  getAllPostsByUserId(userId): Observable<any> {
    return this.httpClient.get<any>(`http://localhost:8765/post-service/api/v1/posts/${userId}`);
  }

}
