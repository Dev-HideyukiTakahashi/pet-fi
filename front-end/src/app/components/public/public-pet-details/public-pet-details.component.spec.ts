import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicPetDetailsComponent } from './public-pet-details.component';

describe('PublicPetDetailsComponent', () => {
  let component: PublicPetDetailsComponent;
  let fixture: ComponentFixture<PublicPetDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicPetDetailsComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(PublicPetDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
