import { Component, ViewEncapsulation } from '@angular/core';
import { publicFooterComponent } from '../public-footer/public-footer.component';
import { publicMenuComponent } from '../public-menu/public-menu.component';

@Component({
  selector: 'app-pet-list',
  standalone: true,
  imports: [publicFooterComponent, publicMenuComponent],
  templateUrl: './pet-list.component.html',
  styleUrl: './pet-list.component.css',
  encapsulation: ViewEncapsulation.None

})
export class PublicPetListComponent {

}
