import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventsgeneralComponent } from './eventsgeneral.component';

describe('EventsgeneralComponent', () => {
  let component: EventsgeneralComponent;
  let fixture: ComponentFixture<EventsgeneralComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsgeneralComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventsgeneralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
