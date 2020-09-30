import { Component, OnInit } from '@angular/core';
import { Circle } from 'src/app/model/circle';
import { Post } from 'src/app/model/post';
import { User } from 'src/app/model/user';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { CircleService } from 'src/app/services/circle.service';
import { CircledataService } from 'src/app/services/circledata.service';
import { DataService } from 'src/app/services/data.service';
import { PostService } from 'src/app/services/post.service';
import { PostdataService } from 'src/app/services/postdata.service';

@Component({
  selector: 'app-circle-profile',
  templateUrl: './circle-profile.component.html',
  styleUrls: ['./circle-profile.component.css']
})
export class CircleProfileComponent implements OnInit {
  members:User[]=[];
  posts:Post[]=[];
  circle: Circle;
  post:Post;
  post1:Post;
  constructor(private dataService: CircledataService, private circleService: CircleService,private postService: PostService,private authService:AuthServiceService,private postdataService:PostdataService) { 
    this.circle = new Circle();
    this.post = new Post();
    this.post1=new Post();
    this.dataService.shareDataSubject.subscribe(
      data => {
        console.log(data);
        this.circle = data;
        this.postService.getAllPostBycircleId().subscribe(
          data=>{
            data.forEach(element => {
              if(element.circleId!=null){
                console.log(element);
                
                if(element.circleId==this.circle.circleId){
                  this.posts.push(element);
                }}                         
            });
            this.postdataService.shareDataSubject.next(this.posts);
            this.postdataService.shareDataSubject.subscribe(
              data=>{
                this.post=data;
              }
            )
          },
          error=>{
            this.posts=[];
          }
        );
        this.circleService.getUsersByCircleId(this.circle.circleId).subscribe(
          data=>{
            console.log(this.circle.circleId);
            this.members=data;
            console.log(data); 
            
          },
          error=>{
            console.error();
            
          }
        )
      }
    );
    console.log(this.circle);
    
    


  
  }

  ngOnInit() {
     
  }

  openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
  }
  addPost() {
    console.log(this.post1);
    this.post1.userId = this.authService.getActiverUser();
    this.post1.postId = this.authService.getActiverUser() + Math.floor(Math.random() * 1000);
    this.post1.circleId=this.circle.circleId;
    console.log(this.post1);
    
    this.postService.addPost(this.post1).subscribe(
      data => {
        this.posts.push(this.post1);
        this.postdataService.shareDataSubject.next(this.posts);

      },
      error => {
        console.log(error);
      }
    );
  
}
deletePost(post) {
  console.log(post);
  post.userId=this.authService.getActiverUser();
  this.postService.deletePost(post).subscribe(
    data => {
      let index=this.posts.indexOf(data);
      this.posts.splice(index,1);
      this.postdataService.shareDataSubject.next(this.posts);
    },
    error => {
      console.log(error);
    }
  );
}

sendPost(post:Post) {
  post.userId = this.authService.getActiverUser();
  console.log(post);
  this.dataService.sendDataToOtherComponent(post);
  
}
}
