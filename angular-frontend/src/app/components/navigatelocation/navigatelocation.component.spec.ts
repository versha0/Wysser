import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavigatelocationComponent } from './navigatelocation.component';

describe('NavigatelocationComponent', () => {
  let component: NavigatelocationComponent;
  let fixture: ComponentFixture<NavigatelocationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavigatelocationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigatelocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
