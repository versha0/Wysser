import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactusreplypopupComponent } from './contactusreplypopup.component';

describe('ContactusreplypopupComponent', () => {
  let component: ContactusreplypopupComponent;
  let fixture: ComponentFixture<ContactusreplypopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContactusreplypopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactusreplypopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
