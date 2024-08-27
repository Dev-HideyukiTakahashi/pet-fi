import { Component, inject } from '@angular/core';
import { SideMenuComponent } from '../../layouts/side-menu/side-menu.component';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Pets } from '../../../models/pets';
import { PetService } from '../../../services/pet.service';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Client } from '../../../models/client';
import Swal from 'sweetalert2';
import { ClientService } from '../../../services/client.service';

@Component({
  selector: 'app-pet-search',
  standalone: true,
  imports: [SideMenuComponent, MdbFormsModule, FormsModule],
  templateUrl: './pet-search.component.html',
  styleUrl: './pet-search.component.css'
})
export class PetSearchComponent {

  pets: Pets[] = [];
  petService = inject(PetService);
  clientService = inject(ClientService);
  router = inject(Router);

  idPet!: number;
  petName!: string;
  search!: boolean;



  findPetById(id: number) {
    if (id == null) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha o campo para realizar a busca!",
      });
    } else {
      this.pets = [];
      this.petService.findById(id).subscribe({
        next: response => this.pets.push(response),
        error: e => { Swal.fire("Código não localizado!"), this.idPet = Number(undefined) },
      })
    }
  }

  findPetByName(petName: string) {
    console.log(petName);
    if (petName == null) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha o campo para realizar a busca!",
      });
    } else {
      this.pets = [];
      this.petService.findByName(petName).subscribe({
        next: response => this.pets = response.content,
        error: e => { Swal.fire("Nome não localizado!"), this.petName = "" },
      })
    }
  }

  navigateEdit(petId: number, clientData: Client) {
    this.router.navigate(["/admin/home/pet/edit/" + petId], { state: { client: clientData } });
  }

  deletePet(id: number) {
    Swal.fire({
      title: "Deletar pet",
      text: "Tem certeza que deseja deletar pet?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Sim",
      cancelButtonText: "Não"
    }).then((result) => {
      if (result.isConfirmed) {
        this.petService.deleteById(id).subscribe({
          next: response => location.reload(),
          error: e => console.log(e.error.message),
        });
      }
    });
  }

}
