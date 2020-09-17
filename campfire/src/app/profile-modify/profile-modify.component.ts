import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient, HttpRequest } from '@angular/common/http';
import { UploadService } from '../shared/upload.service';
import { IProfile } from '../profile';
import { UserService } from '../shared/user.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-modify',
  templateUrl: './profile-modify.component.html',
  styleUrls: ['./profile-modify.component.css']
})
export class ProfileModifyComponent implements OnInit {

  uploadURL = 'http://localhost:9001/CampFire_WAR/updateTitleBioProfile.app';
  imageURL = '../../assets/images/profile_picture.svg';
  updateForm: FormGroup;
  currentUser: IProfile;
  title: string;
  bio: string;

  constructor(public fb: FormBuilder,  private uploadService: UploadService, private userService: UserService, 
              private snackBar: MatSnackBar, private router: Router) {
    this.updateForm = this.fb.group({
      avatar: [null],
      title: [''],
      bio: ['']
    });
  }

  ngOnInit() {
    this.userService.curUser$.subscribe(user => this.currentUser = user);
    this.imageURL = this.currentUser.cf_User_ProfilePic;
    this.title = this.currentUser.cf_User_Title;
    this.bio  = this.currentUser.cf_User_Bio;
  }

   // Image Preview
  showPreview(event) {
    console.log(event.target.files[0]);
    const file = (event.target as HTMLInputElement).files[0];
    this.updateForm.patchValue({
      avatar: file
    });
    this.updateForm.get('avatar').updateValueAndValidity();

    // File Preview
    const reader = new FileReader();
    reader.onload = () => {
      this.imageURL = reader.result as string;
    };
    reader.readAsDataURL(file);
  }

  // Submit Form
  submit() {
    console.log(this.updateForm.value);
    const formData: any = new FormData();
    console.log(this.imageURL);
    console.log(this.updateForm.get('avatar').value);
    formData.append('username', this.currentUser.cf_User_Username);
    formData.append('cf_User_Title', this.updateForm.get('title').value);
    formData.append('cf_User_Bio', this.updateForm.get('bio').value);
    console.log(this.updateForm.get('avatar').value);
    formData.append('cf_User_ProfilePic', this.updateForm.get('avatar').value ?
    this.updateForm.get('avatar').value : this.currentUser.cf_User_ProfilePic);

    this.uploadService.uploadData(this.uploadURL, formData).subscribe(
         (response) => {
          this.snackBar.open('Your profile was successfully updated!',
          'Dismiss', {duration: 5000, verticalPosition: 'top'});
          console.log(response);
          if (this.updateForm.get('bio').value !== '') {
            this.currentUser.cf_User_Bio = this.updateForm.get('bio').value;
          }
          if (this.updateForm.get('title').value !== '') {
            this.currentUser.cf_User_Title = this.updateForm.get('title').value;
          }
          this.currentUser.cf_User_ProfilePic = this.imageURL;
          this.userService.updateCurrentUser(this.currentUser);
          },
         (error) => console.log(error)
       );
    this.router.navigateByUrl('/profile');
    this.userService.updateCurrentProfile(this.currentUser);
  }


}
