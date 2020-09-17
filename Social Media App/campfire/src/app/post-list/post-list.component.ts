import { Component, OnInit, Input } from '@angular/core';
import { IPost } from '../post';
import { ILike } from '../like';
import { PostService } from '../shared/post.service';
import { LikeService } from '../shared/like.service';
import { isPromiseAlike } from 'q';
import { UserService } from '../shared/user.service';
import { IProfile } from '../profile';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {
  myPosts: IPost[] = [];
  allLikes: ILike[] = [];
  myPoststoDisplay: IPost[] = [];
  filterValue = 0;
  currentUser: IProfile;
  currentProfile: IProfile;
  postSource: IPost[] = [];

  get postFilter() {
    return this.filterValue;
  }
  set postFilter(value: number) {
    this.filterValue = value;
    this.myPoststoDisplay = this.filterValue ? this.filterPosts(this.filterValue) : this.myPosts;
  }

  constructor(private postService: PostService, private likeService: LikeService, private userService: UserService, 
              private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.userService.curUser$.subscribe(user => this.currentUser = user);
    this.userService.curProfile$.subscribe(profile => this.currentProfile = profile);
    this.postService.postSource$.subscribe( posts => this.postSource = posts);

    this.myPosts = this.postService.getPosts();
    //this.myPoststoDisplay = [];
    if (this.currentProfile === null) {
      this.myPoststoDisplay = this.myPosts;
      this.postService.updatePostSource(this.myPosts);
    } else {
      this.myPoststoDisplay = [];
      this.myPosts.forEach(post => {
        if (post.userid === this.currentProfile.cf_User_UserId) {
          this.myPoststoDisplay.push(post);
          this.postService.updatePostSource(this.myPoststoDisplay);
        }
      });
  }
    this.likeService.retrieveLikes().subscribe(data => {
      data.forEach(like => {
        this.likeService.addLike({
          likecreated: like[0],
          userid: like[1],
          postid: like[2],
        });
      });
    });

    this.allLikes = this.likeService.getLikesArray();
  }

  //this.userService.curUser$.subscribe(user => this.currentUser = user);

  filterPosts(value: number): IPost[] {
    return this.myPosts.filter((post: IPost) =>
      post.userid === value);
  }

  onLikeClick(p: IPost) {
    // tslint:disable-next-line: forin
    for (let i in this.allLikes) {
      //The 1 should be the userid from the userservice using the subscribe in the ngOnInit
      if ((this.allLikes[i].postid === p.postid) && (this.allLikes[i].userid === this.currentUser.cf_User_UserId)) {

        this.snackBar.open('You have already liked it',
          'Dismiss', {duration: 5000, verticalPosition: 'top'});
          return console.log("YOU CAN'T UNLIKE");
      }
    }
    let myLike: ILike;
    myLike = { likecreated: new Date(), userid: this.currentUser.cf_User_UserId, postid: p.postid };
    this.likeService.insertLikeinDB(myLike).subscribe();
    this.allLikes = this.likeService.addLike(myLike);
    p.likecount++;
  }
}
