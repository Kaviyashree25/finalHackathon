import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BankDashBoardComponent } from './bank-dash-board.component';

describe('BankDashBoardComponent', () => {
  let component: BankDashBoardComponent;
  let fixture: ComponentFixture<BankDashBoardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BankDashBoardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BankDashBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
