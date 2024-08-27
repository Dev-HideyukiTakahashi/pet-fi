import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientSearchComponent } from './client-search.component';

describe('ClientSearchComponent', () => {
  let component: ClientSearchComponent;
  let fixture: ComponentFixture<ClientSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientSearchComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ClientSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
