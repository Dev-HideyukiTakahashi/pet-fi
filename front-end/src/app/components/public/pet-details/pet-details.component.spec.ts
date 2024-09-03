import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetDetailsComponent } from './pet-details.component';

describe('PetDetailsComponent', () => {
  let component: PetDetailsComponent;
  let fixture: ComponentFixture<PetDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetDetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PetDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
