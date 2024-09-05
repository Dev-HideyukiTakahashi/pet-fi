import { Component, ViewEncapsulation } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { MenuComponent } from '../menu/menu.component';

@Component({
  selector: 'app-pet-list',
  standalone: true,
  imports: [FooterComponent, MenuComponent],
  templateUrl: './pet-list.component.html',
  styleUrl: './pet-list.component.css',
  encapsulation: ViewEncapsulation.None

})
export class PetListComponent {

}
