import { TestBed } from '@angular/core/testing';

import { EnfermedadesService } from './enfermedades.service';

describe('EnfermedadesService', () => {
  let service: EnfermedadesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnfermedadesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
