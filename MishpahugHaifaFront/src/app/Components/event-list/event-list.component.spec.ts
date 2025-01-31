import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventlistComponent } from './event-list.component';

describe('EventlistComponent', () => {
  let component: EventlistComponent;
  let fixture: ComponentFixture<EventlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
