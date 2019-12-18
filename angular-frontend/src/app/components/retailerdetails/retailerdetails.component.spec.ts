import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetailerdetailsComponent } from './retailerdetails.component';

describe('RetailerdetailsComponent', () => {
  let component: RetailerdetailsComponent;
  let fixture: ComponentFixture<RetailerdetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetailerdetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetailerdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
