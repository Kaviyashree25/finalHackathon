import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CircleRequestsComponent } from './circle-requests.component';

describe('CircleRequestsComponent', () => {
  let component: CircleRequestsComponent;
  let fixture: ComponentFixture<CircleRequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CircleRequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CircleRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
