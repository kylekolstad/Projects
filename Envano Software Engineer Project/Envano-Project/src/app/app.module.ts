import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostsComponent } from './posts/posts.component';

import { HttpClientModule} from '@angular/common/http'
import { PostsService} from './services/posts.service';
import { UserService} from './services/user.service';
import { MediaService} from './services/media.service';
import { NavigationComponent } from './navigation/navigation.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component'

@NgModule({
  declarations: [
    AppComponent,
    PostsComponent,
    NavigationComponent,
    PagenotfoundComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    PostsService,
    MediaService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
