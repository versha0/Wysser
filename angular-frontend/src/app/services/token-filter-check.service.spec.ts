import { TestBed } from '@angular/core/testing';

import { TokenFilterCheckService } from './token-filter-check.service';

describe('TokenFilterCheckService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TokenFilterCheckService = TestBed.get(TokenFilterCheckService);
    expect(service).toBeTruthy();
  });
});
