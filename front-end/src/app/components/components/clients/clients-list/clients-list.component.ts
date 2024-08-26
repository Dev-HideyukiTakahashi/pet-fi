import { Component, inject } from '@angular/core';
import Swal from 'sweetalert2';
import { Client } from '../../../../models/client';
import { ClientService } from '../../../../services/client.service';
import { Router, RouterLink } from '@angular/router';
import { Pets } from '../../../../models/pets';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-clients-list',
  standalone: true,
  imports: [MdbFormsModule, FormsModule, RouterLink, CommonModule],
  templateUrl: './clients-list.component.html',
  styleUrl: './clients-list.component.css'
})
export class ClientsListComponent {


  clients: Client[] = [];
  clientService = inject(ClientService);
  router = inject(Router);
  totalPages!: number[];
  count: number = 0;

  pet1: Pets = new Pets();
  pet2: Pets = new Pets();

  constructor() {

    this.findAll();

    //teste
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

    //teste fim
  }

  findClient() {
    this.router.navigate(["admin/home/client"]);
  }

  newClient() {
    this.router.navigate(["admin/home/client/new"]);
  }

  // método no back-end findAll paginado
  findAll() {
    this.clientService.findAll().subscribe(res => {
      this.clients = res.content;
      this.totalPages = new Array(res.totalPages);
    });
  }

  // método no back-end findAll paginado com parâmetro de página - response da tag pagination do html
  findAllByPage(page: number) {
    this.clientService.findAllByPage(page).subscribe(res => {
      this.clients = res.content;
    });
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
          next: response => this.findAll(),
          error: e => console.log("Error : " + e.error.message),
        });
        Swal.fire({
          title: "Deletado com sucesso!",
          text: "Cadastro removido.",
          icon: "success"
        });
      }
    });
  }
}
