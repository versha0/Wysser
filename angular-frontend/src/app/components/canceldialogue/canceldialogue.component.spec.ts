import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CanceldialogueComponent } from './canceldialogue.component';

describe('CanceldialogueComponent', () => {
  let component: CanceldialogueComponent;
  let fixture: ComponentFixture<CanceldialogueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CanceldialogueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CanceldialogueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
