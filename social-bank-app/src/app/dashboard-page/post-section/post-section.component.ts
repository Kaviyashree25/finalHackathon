import { Component, Inject, OnInit } from '@angular/core';
import { Post } from 'src/app/model/post';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { DataService } from 'src/app/services/data.service';
import { PostService } from 'src/app/services/post.service';
import { RouterService } from 'src/app/services/router.service';

@Component({
  selector: 'app-post-section',
  templateUrl: './post-section.component.html',
  styleUrls: ['./post-section.component.css']
})

export class PostSectionComponent implements OnInit {

  postList: Post[] = [];
  newPostList: Post[] = [];
  post: Post;
  userId: string;
  postMessage: string;

  constructor(private postService: PostService, private authService: AuthServiceService, private dataService: DataService, private routerService: RouterService) {
    this.post = new Post();
    this.userId = this.authService.getActiverUser();
  }

  ngOnInit() {
    this.postService.getAllPostsByUserId(this.userId).subscribe(
      data => {
        this.postList = data;
        for(var posts of this.postList){
          posts.userId = this.userId;
          this.newPostList.push(posts);
        }
      },
      error => {
        console.log(error);
        this.postMessage = "No posts yet, Start Posting";
      }
    );
  }

  addPost() {
    console.log(this.post);
    this.post.userId = this.authService.getActiverUser();
    this.post.postId = this.authService.getActiverUser() + Math.floor(Math.random() * 1000);
    console.log(this.post);
    
    this.postService.addPost(this.post).subscribe(
      data => {
        console.log(data);
        this.postMessage = 'Post created Successfully!'
      },
      error => {
        console.log(error);
        this.postMessage = error.message;
      }
    );
    location.reload();
  }

  deletePost(post) {
    console.log(post);
    this.postService.deletePost(post).subscribe(
      data => {
        console.log(data);
        let index = this.newPostList.indexOf(post);
        this.newPostList.splice(index, 1);
      },
      error => {
        console.log(error);
      }
    );
  }

  sendPost(post:Post) {
    post.userId = this.userId;
    console.log(post);
    this.dataService.sendDataToOtherComponent(post);
    
  }
}





