import { Component, inject } from '@angular/core';
import { Client } from '../../../../models/client';
import { Router } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms';
import { ClientService } from '../../../../services/client.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-client-search',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './client-search.component.html',
  styleUrl: './client-search.component.css'
})
export class ClientSearchComponent {

  clientService = inject(ClientService);
  router = inject(Router);
  clients: Client[] = [];
  idClient!: number;
  phoneClient!: string;
  nameClient!: string;
  instagramClient!: string;

  findClientById(id: number) {
    if (this.idClient == null) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha o campo para realizar a busca!",
      });
    } else {
      this.clients = [];
      this.clientService.findById(id).subscribe({

        next: response => {
          if (response != null) { this.clients.push(response) }
          else { Swal.fire("Código não localizado!") }
        },
        error: e => { console.log(e.error.message) },
      })
    }
  }

  findClientByPhone(phone: string) {
    if (this.phoneClient == null) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha o campo para realizar a busca! \nTelefone apenas números!",
      });
      this.phoneClient = "";
    } else {
      this.clients = [];
      this.clientService.findByPhone(phone).subscribe({
        next: response => {
          if (response.content.length > 0) { this.clients = response.content }
          else { Swal.fire("Telefone não localizado!"), this.phoneClient = "" }
        },
        error: e => { console.log(e.error.message) },
      });
    }
  }

  findClientByName(name: string) {
    if (this.nameClient == null) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha o campo para realizar a busca!",
      });
    } else {
      this.clientService.findByName(name).subscribe({
        next: response => {
          if (response.content.length > 0) { this.clients = response.content }
          else { Swal.fire("Nome não localizado!"), this.nameClient = "" }
        },
        error: e => { console.log(e.error.message) },
      });
    }
  }

  findClientByInstagram(instagram: string) {
    if (this.instagramClient == null) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha o campo para realizar a busca!",
      });
    } else {
      if (!instagram.startsWith("@")) {
        instagram = "@" + instagram;
      }
      this.clientService.findByInstagram(instagram).subscribe({
        next: response => {
          if (response.content.length > 0) { this.clients = response.content }
          else { Swal.fire("Instagram não localizado!"), this.instagramClient = "" }
        },
        error: e => { console.log(e.error.message) },
      });
    }
  }

  findClient() {
    this.router.navigate(["admin/home/client"]);
  }

  newClient() {
    this.router.navigate(["admin/home/client/new"]);
  }
}
