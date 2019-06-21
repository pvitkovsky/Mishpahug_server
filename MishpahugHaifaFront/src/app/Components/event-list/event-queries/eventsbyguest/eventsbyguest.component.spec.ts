import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventsbyguestComponent } from './eventsbyguest.component';

describe('EventsbyguestComponent', () => {
  let component: EventsbyguestComponent;
  let fixture: ComponentFixture<EventsbyguestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsbyguestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventsbyguestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
