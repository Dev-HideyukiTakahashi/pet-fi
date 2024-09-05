import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicPetListComponent } from './pet-list.component';

describe('PublicPetListComponent', () => {
  let component: PublicPetListComponent;
  let fixture: ComponentFixture<PublicPetListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicPetListComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(PublicPetListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
