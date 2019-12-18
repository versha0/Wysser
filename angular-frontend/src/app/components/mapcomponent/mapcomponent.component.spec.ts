import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MapcomponentComponent } from './mapcomponent.component';

describe('MapcomponentComponent', () => {
  let component: MapcomponentComponent;
  let fixture: ComponentFixture<MapcomponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MapcomponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MapcomponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
