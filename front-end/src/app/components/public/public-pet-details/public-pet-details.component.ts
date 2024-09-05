import { Component, ViewEncapsulation } from '@angular/core';
import { FooterComponent } from '../public-footer/public-footer.component';
import { MenuComponent } from '../public-menu/public-menu.component';
import { Pets } from '../../../models/pets';

@Component({
  selector: 'app-pet-details',
  standalone: true,
  imports: [FooterComponent, MenuComponent],
  templateUrl: './public-pet-details.component.html',
  styleUrl: './public-pet-details.component.css',
  encapsulation: ViewEncapsulation.None
})
export class PublicPetDetailsComponent {

  pet = new Pets();
}
