import {TestBed} from '@angular/core/testing';

import {VehicledemandfrontendService} from './vehicledemandfrontend.service';

describe('VehicledemandfrontendService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VehicledemandfrontendService = TestBed.get(VehicledemandfrontendService);
    expect(service).toBeTruthy();
  });
});
