import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {VehicledemandfrontendComponent} from './vehicledemandfrontend.component';

describe('VehicledemandfrontendComponent', () => {
  let component: VehicledemandfrontendComponent;
  let fixture: ComponentFixture<VehicledemandfrontendComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [VehicledemandfrontendComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicledemandfrontendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
