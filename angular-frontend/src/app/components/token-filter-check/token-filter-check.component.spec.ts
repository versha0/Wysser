import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TokenFilterCheckComponent } from './token-filter-check.component';

describe('TokenFilterCheckComponent', () => {
  let component: TokenFilterCheckComponent;
  let fixture: ComponentFixture<TokenFilterCheckComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TokenFilterCheckComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TokenFilterCheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
