import { Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Client } from '../../../models/client';
import { FormsModule } from '@angular/forms';
import { Pets } from '../../../models/pets';
import Swal from 'sweetalert2';
import { PetService } from '../../../services/pet.service';
import { SideMenuComponent } from '../../layouts/side-menu/side-menu.component';

@Component({
  selector: 'app-pet-details',
  standalone: true,
  imports: [MdbFormsModule, FormsModule, SideMenuComponent],
  templateUrl: './pet-details.component.html',
  styleUrl: './pet-details.component.css'
})
export class PetDetailsComponent {

  router = inject(Router);
  activatedRouter = inject(ActivatedRoute)
  edit: boolean = false;
  client!: Client;
  pet: Pets = new Pets();
  petService = inject(PetService);


  constructor() {
    this.pet.wanted = false;
    this.pet.sex = "Sexo*";
    this.pet.petType = "Pet*";
    this.client = history.state.client;

    let id = this.activatedRouter.snapshot.params['id'];
    if (id != null) {
      this.findById(id);
      this.edit = true;
    }
  }


  findById(id: number) {
    this.petService.findById(id).subscribe({
      next: response => this.pet = response,
      error: e => console.log(e.error.message),
    });
  }

  insert(client: Client, pet: Pets) {
    if (pet.sex == "Sexo*" || pet.petType == "Pet*" || pet.name == null) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha todos os campos obrigatórios!",
      });
    } else {
      this.petService.insert(client.id, pet).subscribe({
        next: response => this.router.navigate(["admin/home/clients/edit/" + client.id]),
        error: e => console.log(e.error.message),
      });
    }
  }

  update(client: Client, pet: Pets) {
    if (pet.sex == "Sexo*" || pet.petType == "Pet*" || pet.name == null) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha todos os campos obrigatórios!",
      });
    } else {
      this.petService.update(pet.id, pet).subscribe({
        next: response => this.router.navigate(["admin/home/clients/edit/" + client.id]),
        error: e => console.log(e.error.message),
      });
    }
  }

  cancel(client: Client) {
    this.router.navigate(["admin/home/clients/edit/" + client.id])
  }

}
