import { Component, inject } from '@angular/core';
import { Client } from '../../../../models/client';
import { Router, RouterLink } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms';
import { ClientService } from '../../../../services/client.service';
import Swal from 'sweetalert2';
import { SideMenuComponent } from '../../../layouts/side-menu/side-menu.component';
import { empty } from 'rxjs';

@Component({
  selector: 'app-client-search',
  standalone: true,
  imports: [MdbFormsModule, FormsModule, RouterLink, SideMenuComponent],
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
  totalPages!: number[];

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

        next: response => this.clients.push(response),
        error: e => { Swal.fire("Código não localizado!"), this.idClient = Number(undefined) },
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

  deleteClient(id: number) {
    Swal.fire({
      title: "Deletar cliente",
      text: "Tem certeza que deseja deletar cliente?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Sim",
      cancelButtonText: "Não"
    }).then((result) => {
      if (result.isConfirmed) {
        this.clientService.deleteById(id).subscribe({
          next: response => location.reload(),
          error: e => console.log("Error : " + e.error.message),
        });
      }
    });
  }
}
