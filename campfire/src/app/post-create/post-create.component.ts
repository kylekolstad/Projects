import { Component, OnInit } from '@angular/core';
import { PostService } from '../shared/post.service';
import { UserService } from '../shared/user.service';
import { IProfile } from '../profile';
import { UploadService } from '../shared/upload.service';


@Component({
  selector: 'app-post-create',
  templateUrl: './post-create.component.html',
  styleUrls: ['./post-create.component.css']
})
export class PostCreateComponent implements OnInit {

  currentUser: IProfile;
  fileCheck: File;
  private uploadURL = 'http://localhost:9001/CampFire_WAR/insertPost.app';

  constructor(private postService: PostService, private userService: UserService, private uploadService: UploadService) { }

  ngOnInit() {
    this.userService.curUser$.subscribe(user => this.currentUser = user);
  }

  fileLoaded(event) {
    console.log(event.target);
    this.fileCheck = (event.target as HTMLInputElement).files[0];

  }

  onSubmit(form: any) {
    console.log(this.fileCheck);
    const formData: any = new FormData();
    formData.append('postcontents', form.value.postContents);
    formData.append('userid', this.currentUser.cf_User_UserId);
    formData.append('file', this.fileCheck);
    this.uploadService.uploadData(this.uploadURL, formData).subscribe((response) => {
    console.log(response);
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
      form.reset();
    });
  });
  }

}
