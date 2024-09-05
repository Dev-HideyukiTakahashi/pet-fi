import { Component } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { MenuComponent } from '../menu/menu.component';

@Component({
  selector: 'app-pet-details',
  standalone: true,
  imports: [FooterComponent, MenuComponent],
  templateUrl: './pet-details.component.html',
  styleUrl: './pet-details.component.css'
})
export class PetDetailsComponent {

}
