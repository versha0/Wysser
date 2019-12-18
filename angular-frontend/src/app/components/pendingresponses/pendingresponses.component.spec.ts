import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingresponsesComponent } from './pendingresponses.component';

describe('PendingresponsesComponent', () => {
  let component: PendingresponsesComponent;
  let fixture: ComponentFixture<PendingresponsesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PendingresponsesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingresponsesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
