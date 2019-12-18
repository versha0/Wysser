import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingVehicleRequestComponent } from './pending-vehicle-request.component';

describe('PendingVehicleRequestComponent', () => {
  let component: PendingVehicleRequestComponent;
  let fixture: ComponentFixture<PendingVehicleRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PendingVehicleRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingVehicleRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
