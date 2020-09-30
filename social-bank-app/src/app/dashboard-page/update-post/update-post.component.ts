import { Component, Inject, OnInit } from '@angular/core';
import { Post } from 'src/app/model/post';
import { DataService } from 'src/app/services/data.service';
import { PostService } from 'src/app/services/post.service';
import { RouterService } from 'src/app/services/router.service';

@Component({
  selector: 'app-update-post',
  templateUrl: './update-post.component.html',
  styleUrls: ['./update-post.component.css']
})
export class UpdatePostComponent implements OnInit {

  post: Post;
  postMessage: string;
  userId: string;
  postId: string;
  constructor(private dataService: DataService, private postService: PostService, private routerService: RouterService) { 
    this.post = new Post();
    this.dataService.shareDataSubject.subscribe(
      data => {
        console.log(data);
        this.post = data;
        console.log(this.post.userId);
        this.userId = this.post.userId;
        this.postId = this.post.postId;
      }
    );
  }

  ngOnInit() {
  }

  updatePost(){
    console.log(this.post);
    
    this.postService.updatePost(this.post).subscribe(
      data => {
        this.postMessage = 'Updated Successfully';
        this.routerService.routeToMyPost();
      },
      error => {
        this.postMessage = error.message;
      }
    )
  }


}
