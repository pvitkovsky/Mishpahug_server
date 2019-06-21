import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventsbyownerComponent } from './eventsbyowner.component';

describe('EventsbyownerComponent', () => {
  let component: EventsbyownerComponent;
  let fixture: ComponentFixture<EventsbyownerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsbyownerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventsbyownerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
