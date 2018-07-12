import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticalStatementsComponent } from './analytical-statements.component';

describe('AnalyticalStatementsComponent', () => {
  let component: AnalyticalStatementsComponent;
  let fixture: ComponentFixture<AnalyticalStatementsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticalStatementsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticalStatementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
