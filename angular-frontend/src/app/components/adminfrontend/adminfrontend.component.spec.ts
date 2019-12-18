import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminfrontendComponent } from './adminfrontend.component';

describe('AdminfrontendComponent', () => {
  let component: AdminfrontendComponent;
  let fixture: ComponentFixture<AdminfrontendComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminfrontendComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminfrontendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
