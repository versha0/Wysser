import { TestBed } from '@angular/core/testing';

import { VechicleCompanyServiceService } from './vechicle-company-service.service';

describe('VechicleCompanyServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VechicleCompanyServiceService = TestBed.get(VechicleCompanyServiceService);
    expect(service).toBeTruthy();
  });
});
