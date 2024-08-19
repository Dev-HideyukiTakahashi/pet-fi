import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetListComponent } from './pet-list.component';

describe('PetListComponent', () => {
  let component: PetListComponent;
  let fixture: ComponentFixture<PetListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PetListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PetListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
