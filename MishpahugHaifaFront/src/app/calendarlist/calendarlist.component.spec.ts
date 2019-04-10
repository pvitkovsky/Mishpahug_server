import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarlistComponent } from './calendarlist.component';

describe('CalendarlistComponent', () => {
  let component: CalendarlistComponent;
  let fixture: ComponentFixture<CalendarlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CalendarlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
