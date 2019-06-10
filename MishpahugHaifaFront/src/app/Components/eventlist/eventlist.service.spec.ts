import { TestBed } from '@angular/core/testing';

import { EventListService } from './eventlist.service';

describe('EventlistserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EventListService = TestBed.get(EventListService);
    expect(service).toBeTruthy();
  });
});
