import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CircleExploreComponent } from './circle-explore.component';

describe('CircleExploreComponent', () => {
  let component: CircleExploreComponent;
  let fixture: ComponentFixture<CircleExploreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CircleExploreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CircleExploreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
