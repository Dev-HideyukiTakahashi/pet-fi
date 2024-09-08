import { Component, inject, ViewEncapsulation } from '@angular/core';
import { publicFooterComponent } from '../public-footer/public-footer.component';
import { publicMenuComponent } from '../public-menu/public-menu.component';
import { FormsModule } from '@angular/forms';
import { ClientService } from '../../../services/client.service';

@Component({
  selector: 'app-pet-list',
  standalone: true,
  imports: [publicFooterComponent, publicMenuComponent, FormsModule],
  templateUrl: './public-pet-list.component.html',
  styleUrl: './public-pet-list.component.css',
  encapsulation: ViewEncapsulation.None

})
export class PublicPetListComponent {

  city = "Cidade";
  cities: string[] = [];

  clientService = inject(ClientService);


  constructor() {
    this.findAllCities();
  }

  findAllCities() {
    this.clientService.findAllCities().subscribe({
      next: response => this.cities = response,
      error: err => console.log(err),
    })
  }

  search() {
    console.log(this.city);
  }
}
