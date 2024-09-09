import { Component, inject, Input, input } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import Swal from 'sweetalert2';
import { Client } from '../../../models/client';
import { FormsModule } from '@angular/forms';
import { MdbValidationModule } from 'mdb-angular-ui-kit/validation';
import { ClientService } from '../../../services/client.service';
import { SideMenuComponent } from '../../layouts/side-menu/side-menu.component';
import { PetService } from '../../../services/pet.service';

@Component({
  selector: 'app-client-details',
  standalone: true,
  imports: [MdbFormsModule, FormsModule, MdbValidationModule, RouterLink, SideMenuComponent],
  templateUrl: './client-details.component.html',
  styleUrl: './client-details.component.css'
})
export class ClientDetailsComponent {

  router = inject(Router);
  activatedRouter = inject(ActivatedRoute);
  client: Client = new Client();
  edit: boolean = false;
  clientService = inject(ClientService);
  petService = inject(PetService);

  constructor() {
    let id = this.activatedRouter.snapshot.params['id'];
    if (id != null) {
      this.findById(id);
      this.edit = true;
    }
  }

  findById(id: number) {
    this.clientService.findById(id).subscribe({
      next: response => this.client = response,
      error: e => e.error.message,
    })
  }

  insert(client: Client) {

    //Checando se telefone apenas numeros
    if (this.phoneOnlyNumber()) {
      Swal.fire("Telefone apenas números!");
    }
    else if (this.client.name == null || this.client.phone == null) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha todos os campos obrigatórios!",
      });
    }
    else {
      if (this.client.instagram != null) {
        // REMOVENDO UM POSSIVEL '@'
        if (this.client.instagram.startsWith("@")) {
          this.client.instagram = this.client.instagram.substring(1);
        }
        // EDITANDO UM CLIENT
        if (this.client.id != null) {
          this.checkDDI();
          this.clientService.update(client.id, client).subscribe({
            next: response => this.router.navigate(["admin/home/clients"], { state: { updatedClient: this.client } }),
            error: e => console.log("Error : " + e.error.message),
          });
          Swal.fire({
            title: 'Editado com sucesso!',
            icon: 'success',
            confirmButtonText: 'Ok'
          })
        }
        // CADASTRANDO UM NOVO CLIENT
        else {
          this.checkDDI();
          this.clientService.insert(client).subscribe({
            next: response => {
              this.client.id = response.id,
                this.registerFirstPet();
            },
            error: e => console.log("Error : " + e.error.message),
          });
        }
      }
    }
  }

  registerFirstPet() {
    Swal.fire({
      title: "Cadastro de cliente realizado!",
      text: "Deseja cadastrar um pet agora?",
      icon: "success",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Sim",
      cancelButtonText: "Não"
    }).then((result) => {
      if (result.isConfirmed) {
        this.router.navigate(["admin/home/pet/new"], { state: { client: this.client } });
      } else {
        this.router.navigate(["admin/home/clients"], { state: { newClient: this.client } })
      }
    });

  }

  checkDDI() {
    if (this.client.phone.charAt(0) != "5" && this.client.phone.charAt(1) != "5") {
      this.client.phone = "" + 55 + this.client.phone;
    }
  }

  phoneOnlyNumber() {
    //Checando cada caractere, se um não for número retorna true
    for (let index = 0; index < this.client.phone.length; index++) {
      let test = Number.parseInt(this.client.phone[index])
      if (isNaN(test)) {
        return true;
      }
    }
    return false;
  }

  registerPet() {
    this.router.navigate(["admin/home/pet/new"], { state: { client: this.client } });
  }

  updatePet(id: number) {
    this.router.navigate(["admin/home/pet/edit/" + id], { state: { client: this.client } });
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

