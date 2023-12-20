import { TestBed } from '@angular/core/testing';

import { MedicinaService } from './medicina.service';

describe('MedicinaService', () => {
  let service: MedicinaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicinaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
