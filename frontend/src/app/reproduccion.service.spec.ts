import { TestBed } from '@angular/core/testing';

import { ReproduccionService } from './reproduccion.service';

describe('ReproduccionService', () => {
  let service: ReproduccionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReproduccionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
