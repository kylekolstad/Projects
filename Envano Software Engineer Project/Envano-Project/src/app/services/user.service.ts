import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl = 'https://www.envano.com/wp-json/wp/v2/users?per_page=100';

  constructor(private _http: HttpClient) { }

  getUsers() {
    return this._http.get<User []>(this.apiUrl);
  }
}
