import {TestBed} from '@angular/core/testing';

import {GetlistService} from './getlist.service';

describe('GetlistService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetlistService = TestBed.get(GetlistService);
    expect(service).toBeTruthy();
  });
});
