import {TestBed} from '@angular/core/testing';

import {DriverDashboardService} from './driver-dashboard.service';

describe('DriverDashboardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DriverDashboardService = TestBed.get(DriverDashboardService);
    expect(service).toBeTruthy();
  });
});
