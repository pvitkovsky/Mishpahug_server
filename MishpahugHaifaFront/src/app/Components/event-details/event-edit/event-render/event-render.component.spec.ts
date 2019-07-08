import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventRenderComponent } from './event-render.component';

describe('EventRenderComponent', () => {
  let component: EventRenderComponent;
  let fixture: ComponentFixture<EventRenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventRenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventRenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
