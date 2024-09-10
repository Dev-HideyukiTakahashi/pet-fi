import { Component, inject, ViewEncapsulation } from '@angular/core';
import { publicFooterComponent } from '../public-footer/public-footer.component';
import { publicMenuComponent } from '../public-menu/public-menu.component';
import { Pets } from '../../../models/pets';
import { PetService } from '../../../services/pet.service';
import { ActivatedRoute } from '@angular/router';
import { Client } from '../../../models/client';

@Component({
  selector: 'app-pet-details',
  standalone: true,
  imports: [publicFooterComponent, publicMenuComponent],
  templateUrl: './public-pet-details.component.html',
  styleUrl: './public-pet-details.component.css',
  encapsulation: ViewEncapsulation.None
})
export class PublicPetDetailsComponent {

  petService = inject(PetService);
  activatedRouter = inject(ActivatedRoute);

  pet = new Pets();
  petProcurado!: string;


  constructor() {
    let id = this.activatedRouter.snapshot.params['id'];
    if (id != null) {
      this.pet.client = new Client();
      this.petService.findById(id).subscribe({
        next: response => { this.pet = response, this.petProcurado = this.pet.wanted ? "Sim" : "Não" },
        error: err => console.log(err),
      });
    }
  }


}
