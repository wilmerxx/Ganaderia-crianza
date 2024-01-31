import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumoComponent } from './consumo.component';

describe('ConsumoComponent', () => {
  let component: ConsumoComponent;
  let fixture: ComponentFixture<ConsumoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsumoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsumoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
