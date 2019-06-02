import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyeventlistComponent } from './myeventlist.component';

describe('MyeventlistComponent', () => {
  let component: MyeventlistComponent;
  let fixture: ComponentFixture<MyeventlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyeventlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyeventlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
