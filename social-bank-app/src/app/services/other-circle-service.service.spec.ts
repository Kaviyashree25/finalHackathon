import { TestBed } from '@angular/core/testing';

import { OtherCircleServiceService } from './other-circle-service.service';

describe('OtherCircleServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OtherCircleServiceService = TestBed.get(OtherCircleServiceService);
    expect(service).toBeTruthy();
  });
});
