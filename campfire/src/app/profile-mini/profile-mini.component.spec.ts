import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileMiniComponent } from './profile-mini.component';

describe('ProfileMiniComponent', () => {
  let component: ProfileMiniComponent;
  let fixture: ComponentFixture<ProfileMiniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfileMiniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileMiniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
