
import { Component, inject, Input, } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Client } from '../../../../models/client';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { RouterLink } from '@angular/router';
import { Pets } from '../../../../models/pets';
import { ClientService } from '../../../../services/client.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-client-menu',
  standalone: true,
  imports: [MdbFormsModule, FormsModule, RouterLink, CommonModule],
  templateUrl: './client-menu.component.html',
  styleUrl: './client-menu.component.css'
})
export class ClientMenuComponent {

  clients: Client[] = [];
  clientService = inject(ClientService);
  router = inject(Router);
  totalPages!: number[];
  count: number = 0;

  pet1: Pets = new Pets();
  pet2: Pets = new Pets();

  constructor() {

    this.findAll();

    let newClient = history.state.newClient;
    let updatedClient = history.state.updatedClient;

    if (newClient) {
      this.clients.push(newClient);
    }
    if (updatedClient) {
      let index = this.clients.findIndex(x => {
        return x.id == updatedClient.id
      });
      this.clients[index] = updatedClient;
    }
  }

  // método no back-end findAll paginado
  findAll() {
    this.clientService.findAll().subscribe(res => {
      this.clients = res.content.reverse();
      let totalPages: number[] = new Array(res.totalPages);
      this.totalPages = totalPages;

    });
  }

  // método no back-end findAll paginado com parâmetro de página
  findAllByPage(page: number) {
    this.clientService.findAllByPage(page).subscribe(res => {
      this.clients = res.content.reverse();
    });
  }

  findClient() {
    this.router.navigate(["admin/home/client"]);
  }

  newClient() {
    this.router.navigate(["admin/home/clients/new"]);
  }

  deleteClient(client: Client) {
    let index = this.clients.findIndex(x => {
      return x.id == client.id;
    })
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
        this.clients.splice(index, 1);
        Swal.fire({
          title: "Deletado com sucesso!",
          text: "Cadastro removido.",
          icon: "success"
        });
      }
    });

  }
}


