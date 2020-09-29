import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CircleProfileComponent } from './circle-profile.component';

describe('CircleProfileComponent', () => {
  let component: CircleProfileComponent;
  let fixture: ComponentFixture<CircleProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CircleProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CircleProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
