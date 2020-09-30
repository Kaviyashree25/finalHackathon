import { TestBed } from '@angular/core/testing';

import { CircledataService } from './circledata.service';

describe('CircledataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CircledataService = TestBed.get(CircledataService);
    expect(service).toBeTruthy();
  });
});
