import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { IProfile } from '../profile';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class RegisterService {

  private url = 'http://localhost:9001/CampFire_WAR/createUser.app';

  constructor(private http: HttpClient) { }

  createNewUser(form: any): Observable<number> {

    const params = new HttpParams()
    .set('username', form.value.username)
    .set('userpassword', form.value.password)
    .set('userfname', form.value.firstname)
    .set('userlname', form.value.lastname)
    .set('useremail', form.value.email)
    .set('userbirthday', form.value.dob)
    .set('usertitle', form.value.title)
    .set('userbio', '')
    .set('userprofilepic', 'https://campfire-project2.s3.us-east-2.amazonaws.com/profile_picture.svg');

    return this.http.post<number>(this.url, params, {withCredentials: true});
  }

}
