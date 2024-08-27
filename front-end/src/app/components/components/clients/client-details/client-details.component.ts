import { Component, inject, Input, input } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import Swal from 'sweetalert2';
import { Client } from '../../../../models/client';
import { FormsModule } from '@angular/forms';
import { MdbValidationModule } from 'mdb-angular-ui-kit/validation';
import { ClientService } from '../../../../services/client.service';
import { SideMenuComponent } from '../../../layouts/side-menu/side-menu.component';

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
  clientService = inject(ClientService)

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
    if (this.client.name == null || this.client.city == null || this.client.phone == null) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha todos os campos obrigatórios!",
      });
    } else {
      if (this.client.instagram != null) {
        if (!this.client.instagram.startsWith("@")) {
          this.client.instagram = "@" + this.client.instagram
        }
        // EDITANDO UM CLIENT
        if (this.client.id != null) {
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
          this.clientService.insert(client).subscribe({
            next: response => this.router.navigate(["admin/home/clients"], { state: { newClient: this.client } }),
            error: e => console.log("Error : " + e.error.message),
          });
          Swal.fire({
            title: 'Sucesso!',
            text: 'Novo cadastro realizado.',
            icon: 'success',
            confirmButtonText: 'Ok'
          })
        }
      }
    }
  }

  registerPet() {
    this.router.navigate(["admin/home/pet/new"], { state: { client: this.client } });
  }

  updatePet(id: number) {
    this.router.navigate(["admin/home/pet/edit/" + id], { state: { client: this.client } });
  }
}

