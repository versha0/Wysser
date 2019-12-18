import { TestBed } from '@angular/core/testing';

import { DatafromrouteoptimizerService } from './datafromrouteoptimizer.service';

describe('DatafromrouteoptimizerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DatafromrouteoptimizerService = TestBed.get(DatafromrouteoptimizerService);
    expect(service).toBeTruthy();
  });
});
