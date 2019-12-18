import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymemonthlyComponent } from './paymemonthly.component';

describe('PaymemonthlyComponent', () => {
  let component: PaymemonthlyComponent;
  let fixture: ComponentFixture<PaymemonthlyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymemonthlyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymemonthlyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
