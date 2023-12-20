import { TestBed } from '@angular/core/testing';

import { GanadoService } from './ganado.service';

describe('GanadoService', () => {
  let service: GanadoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GanadoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
