import { Component, OnInit } from '@angular/core';
import { PostsService } from '../services/posts.service';
import { UserService } from '../services/user.service';
import { MediaService } from '../services/media.service';
import { Post } from '../models/post.model';
import { User } from '../models/user.model';
import { Media } from '../models/media.model';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  posts$: Post[] = null;
  firstPost$: Post;
  users$: User[];
  media$: Media[];
  mediaObject: Media;

  constructor(private postService: PostsService, private userService: UserService, private mediaService: MediaService) { }

  ngOnInit() {
    this.getAllPosts();
    this.getAllUsers();
    this.getAllMedia();
  }

  getScreenWidth() {
    return screen.width;
  }
  goToPost(link: string) {
    window.open(link, "_blank");
  }

  getCurrentPage() {
    return this.postService.page;
  }

  getTotalPages() {
    return this.postService.page;
  }

  nextPage() {
    if (this.postService.page < this.postService.totalPages) {
      this.toTop();
      this.postService.getNextPage();
      this.ngOnInit();
    }
  }

  previousPage() {
    if (this.postService.page > 1) {
      this.toTop();
      this.postService.getPreviousPage();
      this.ngOnInit();
    }
  }

  getMedia(_featured_media: number) {
    for (let i = 0; i < this.media$.length; i++) {
      if (this.media$[i].id == _featured_media)
        return this.media$[i].source_url;
    }
  }

  getAllMedia() {
    return this.mediaService.getMedia().subscribe(media => {
      this.media$ = media
    });
    
  }

  getAllUsers() {
    return this.userService.getUsers().subscribe(user => this.users$ = user);
  }

  getAllPosts() {
    return this.postService.getPosts().subscribe(post => {
      this.posts$ = post
      this.firstPost$ = post[0];
    });
  }

  getUserName(_id: number) {
    for (let i = 0; i < this.users$.length; i++) {
      if (this.users$[i].id == _id) {
        return this.users$[i].name;
      }
    }
  }

  getUserAvatar(_id: number) {
    for (let i = 0; i < this.users$.length; i++) {
      if (this.users$[i].id == _id) {
        return this.users$[i].mpp_avatar.full;
      }
    }
  }

  toTop() {
    document.getElementById("top").scrollIntoView({ behavior: "smooth" });
  }
}
