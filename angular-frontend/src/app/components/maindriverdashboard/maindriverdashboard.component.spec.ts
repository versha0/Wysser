import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaindriverdashboardComponent } from './maindriverdashboard.component';

describe('MaindriverdashboardComponent', () => {
  let component: MaindriverdashboardComponent;
  let fixture: ComponentFixture<MaindriverdashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaindriverdashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaindriverdashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
