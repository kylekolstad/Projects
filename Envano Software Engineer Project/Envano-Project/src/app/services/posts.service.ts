import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from '../models/post.model';
import { catchError } from 'rxjs/operators'
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostsService {
  page: number = 1;
  resultsPerPage: number = 7;
  totalPages: number = 50;
  apiUrl: string;

  constructor(private _http: HttpClient) {
  }

  getPosts() {
    this.apiUrl = 'https://www.envano.com/wp-json/wp/v2/posts?per_page=' + this.resultsPerPage + '&page=' + this.page;
    return this._http.get<Post[]>(this.apiUrl).pipe(catchError(this.handleError));
  }

  getNextPage() {
    this.page++;
  }

  getPreviousPage() {
    this.page--;
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
        
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }
}
