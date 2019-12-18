import {TestBed} from '@angular/core/testing';

import {VehicleRentService} from './vehicle-rent.service';

describe('VehicleRentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VehicleRentService = TestBed.get(VehicleRentService);
    expect(service).toBeTruthy();
  });
});
