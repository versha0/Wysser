import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HelpboardComponent } from './helpboard.component';

describe('HelpboardComponent', () => {
  let component: HelpboardComponent;
  let fixture: ComponentFixture<HelpboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HelpboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HelpboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
