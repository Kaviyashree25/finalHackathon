import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CircleUpdateComponent } from './circle-update.component';

describe('CircleUpdateComponent', () => {
  let component: CircleUpdateComponent;
  let fixture: ComponentFixture<CircleUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CircleUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CircleUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
