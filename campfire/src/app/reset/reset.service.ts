import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NgForm } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ResetService {

  private url = 'http://localhost:9001/CampFire_WAR/updatePassword.app';

  constructor(private http: HttpClient) { }

  restPassword(form: NgForm): Observable<number> {
    const params = new HttpParams()
    .set('username', form.value.username)
    .set('email', form.value.email);
    return this.http.post<number>(this.url, params,  {withCredentials: true});
  }
}
