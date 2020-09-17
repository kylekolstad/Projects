import { Component, OnInit } from '@angular/core';
import { IProfile } from '../profile';
import { UserService } from '../shared/user.service';
import { Router } from '@angular/router';
import { PostService } from '../shared/post.service';
import { IPost } from '../post';
@Component({
  selector: 'app-profile-list',
  templateUrl: './profile-list.component.html',
  styleUrls: ['./profile-list.component.css']
})
export class ProfileListComponent implements OnInit {
  myUsers: IProfile[] = [];
  myUsersToDisplay: IProfile[] = [];
  filterValue = '';
  currentUser: IProfile;
  postList: IPost[];

  get userFilter() {
    return this.filterValue;
  }
  set userFilter(value: string) {
    this.filterValue = value;
    this.myUsersToDisplay = this.filterValue ? this.filterUser(this.filterValue) : this.myUsers;
  }
  constructor(private userService: UserService, private router: Router, private postService: PostService) { }
  ngOnInit() {
    this.userService.curUser$.subscribe(user => this.currentUser = user);
    this.myUsers = this.userService.getUsers();
    this.myUsersToDisplay = this.myUsers;
  }
  filterUser(value: string): IProfile[] {
    value = value.toLocaleLowerCase();
    return this.myUsers.filter((user: IProfile) =>
      user.cf_User_Fname.toLocaleLowerCase().indexOf(value) !== -1 || user.cf_User_Lname.toLocaleLowerCase().indexOf(value) !== -1
    );
  }
  setCurrentProfile(user: IProfile) {
    this.postList = [];
    this.userService.updateCurrentProfile(user);
    this.postService.getPosts().forEach( post => {
      if (post.userid === user.cf_User_UserId) {
        this.postList.push(post);
      }
    });
    this.postService.updatePostSource(this.postList);
    this.router.navigateByUrl('/friend');
    document.getElementById('header').innerHTML =  user.cf_User_Fname + ' ' + user.cf_User_Lname +  "'s Profile";
  }
}
