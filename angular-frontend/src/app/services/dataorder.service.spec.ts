import { TestBed } from '@angular/core/testing';

import { DataorderService } from './dataorder.service';

describe('DataorderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DataorderService = TestBed.get(DataorderService);
    expect(service).toBeTruthy();
  });
});
