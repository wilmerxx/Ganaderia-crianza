import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicinaComponent } from './medicina.component';

describe('MedicinaComponent', () => {
  let component: MedicinaComponent;
  let fixture: ComponentFixture<MedicinaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicinaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicinaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
