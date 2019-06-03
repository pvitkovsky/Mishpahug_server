import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventsrendererComponent } from './eventsrenderer.component';

describe('EventsrendererComponent', () => {
  let component: EventsrendererComponent;
  let fixture: ComponentFixture<EventsrendererComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsrendererComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventsrendererComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
