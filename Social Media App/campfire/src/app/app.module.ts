import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';
	

import { AppRoutingModule } from './app-routing.module';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProfileComponent } from './profile/profile.component';
import { ProfileMiniComponent } from './profile-mini/profile-mini.component';
import { ProfileModifyComponent } from './profile-modify/profile-modify.component';
import { PostComponent } from './post/post.component';
import { PostListComponent } from './post-list/post-list.component';
import { PostCreateComponent } from './post-create/post-create.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ResetComponent } from './reset/reset.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HeaderComponent } from './header/header.component';
import { ProfileListComponent } from './profile-list/profile-list.component';
import { MainfeedComponent } from './mainfeed/mainfeed.component';
import { LoginService } from './login/login.service';
import { RegisterService } from './register/register.service';
import { ResetService } from './reset/reset.service';
import { FriendComponent } from './friend/friend.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'reset', component: ResetComponent },
  { path: 'navbar', component: NavbarComponent },
  { path: 'login', component: LoginComponent },
  { path: 'header', component: HeaderComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'profilemini', component: ProfileMiniComponent },
  { path: 'editprofile', component: ProfileModifyComponent },
  { path: 'profilelist', component: ProfileListComponent },
  { path: 'post', component: PostComponent },
  { path: 'postlist', component: PostListComponent },
  { path: 'postcreate', component: PostCreateComponent },
  {path: 'home', component: MainfeedComponent},
  {path: 'friend', component: FriendComponent},
  {path: '**', redirectTo: 'login', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    ProfileMiniComponent,
    ProfileModifyComponent,
    PostComponent,
    PostListComponent,
    PostCreateComponent,
    LoginComponent,
    RegisterComponent,
    ResetComponent,
    NavbarComponent,
    HeaderComponent,
    ProfileListComponent,
    MainfeedComponent,
    FriendComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    ReactiveFormsModule,
    BrowserAnimationsModule,
    BrowserAnimationsModule,
    MatSnackBarModule
],

  exports: [RouterModule],
  providers: [
    LoginService,
    RegisterService,
    ResetService
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
