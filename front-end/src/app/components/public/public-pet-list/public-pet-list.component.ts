import { Component, inject, SimpleChanges, ViewEncapsulation } from '@angular/core';
import { publicFooterComponent } from '../public-footer/public-footer.component';
import { publicMenuComponent } from '../public-menu/public-menu.component';
import { FormsModule } from '@angular/forms';
import { ClientService } from '../../../services/client.service';
import { Pets } from '../../../models/pets';
import { PetService } from '../../../services/pet.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pet-list',
  standalone: true,
  imports: [publicFooterComponent, publicMenuComponent, FormsModule],
  templateUrl: './public-pet-list.component.html',
  styleUrl: './public-pet-list.component.css',
  encapsulation: ViewEncapsulation.None

})
export class PublicPetListComponent {

  petService = inject(PetService);

  city = "Cidade";
  option!: string;
  totalPages!: number[];

  cities = new Set();
  pets: Pets[] = [];

  router = inject(Router);

  constructor() {

    // Populando select de cidades
    this.petService.findAllCities().subscribe({
      next: response => this.fillCities(response),
      error: err => console.log(err)
    })

    // Buscando todos pet desaparecidos
    this.wanted();

  }

  // Buscando todos pet desaparecidos por cidade
  search() {
    if (this.city != "Cidade") {
      this.petService.findAllPetsWantedAndCity(this.city).subscribe({
        next: response => { this.pets = response.content, this.totalPages = new Array(response.totalPages); },
        error: err => console.log(err),
      })
    } else {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Selecione uma cidade para busca!",
      });
    }
  }

  wanted() {
    this.petService.findAllPetsWanted().subscribe({
      next: response => {
        this.pets = response.content,
          this.totalPages = new Array(response.totalPages);
      },
      error: err => console.log(err),
    })
  }

  findAllByPage(page: number) {
    if (this.city == "Cidade") {
      this.petService.findAllPetsWantedPaged(page).subscribe({
        next: response => {
          this.pets = response.content,
            this.totalPages = new Array(response.totalPages);
        },
        error: err => console.log(err),
      })
    } else {
      this.petService.findAllPetsWantedAndCityPaged(page, this.city).subscribe({
        next: response => {
          this.pets = response.content,
            this.totalPages = new Array(response.totalPages);
        },
        error: err => console.log(err),
      })
    }

  }

  fillCities(obj: string[]) {
    obj.forEach(element => {
      this.cities.add(element);
    });
  }

  details(id: number) {
    this.router.navigate(["home/pet/" + id])
  }

}
