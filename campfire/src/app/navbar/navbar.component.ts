import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { UserService } from '../shared/user.service';
import { IProfile } from '../profile';
import { LoginService } from '../login/login.service';
import { PostService } from '../shared/post.service';
import { IPost } from '../post';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  private selected = 'mainfeed';
  constructor(private userService: UserService, private loginService: LoginService, private postService: PostService) { }

  currentUser: IProfile;
  currentProfile: IProfile;
  postList: IPost[] = [];

  ngOnInit() {
    this.userService.curUser$.subscribe(user => this.currentUser = user);
  }

  onFeedClick() {
      document.getElementById('header').innerHTML = 'Main Feed';
      this.userService.updateCurrentProfile(null);
      this.postService.clearPosts();
    this.postService.retrievePosts().subscribe(data => {

      data.forEach(post => {
        this.postService.addPost({
          userid: post[0],
          userfname: post[1],
          userlname: post[2],
          userprofurl: post[3],
          usertitle: post[4],
          postid: post[5],
          postcontents: post[6],
          postcreated: post[7],
          postimgurl: post[8],
          likecount: post[9],
        });
      });
      this.postService.updatePostSource(this.postService.getPosts());
      // this.postService.clearPosts();
    });
  }

  onProfileClick() {
      document.getElementById('header').innerHTML = 'My Profile';

      this.postList = [];
      this.userService.updateCurrentProfile(this.currentUser);
      this.postService.getPosts().forEach( post => {
      if (post.userid === this.currentUser.cf_User_UserId) {
        this.postList.push(post);
      }
    });
      this.postService.updatePostSource(this.postList);


      //this.postService.clearPosts();
  }

  onSettingsClick() {
      document.getElementById('header').innerHTML = 'Settings';
  }

  onLogoutClick() {
    this.userService.updateCurrentUser(null);
    this.loginService.logoutUser('http://localhost:9001/CampFire_WAR/logout.app').subscribe();
    this.userService.clearUserData();
  }
}
