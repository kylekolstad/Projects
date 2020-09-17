import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { IPost } from '../post';
import { HttpClient } from '@angular/common/http';
import { UploadService } from './upload.service';


@Injectable({
  providedIn: 'root'
})

export class PostService {

  private url = 'http://localhost:9001/CampFire_WAR/getAllPosts.app';
  private uploadURL = 'http://localhost:9001/CampFire_WAR/insertPost.app';
  // replace with post mocking json

  myPosts: IPost[] = [];
  private postSource = new BehaviorSubject<IPost[]>(null);
  postSource$ = this.postSource.asObservable();

  constructor(private httpCli: HttpClient, private uploadService: UploadService) { }

  retrievePosts(): Observable<IPost[]> {
    return this.httpCli.get<IPost[]>(this.url, { withCredentials: true });
  }

  addPost(post: IPost) {
    this.myPosts.push(post);
  }

  getPosts(): IPost[] {
    return this.myPosts;
  }

  clearPosts() {
    this.myPosts = [];
  }

  updatePostSource(posts: IPost[]) {
    this.postSource.next(posts);
  }
}
