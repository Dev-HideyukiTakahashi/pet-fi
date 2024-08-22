
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Client } from '../../../../models/client';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { RouterLink } from '@angular/router';
import { Pets } from '../../../../models/pets';

@Component({
  selector: 'app-client-menu',
  standalone: true,
  imports: [MdbFormsModule, FormsModule, RouterLink],
  templateUrl: './client-menu.component.html',
  styleUrl: './client-menu.component.css'
})
export class ClientMenuComponent {

  clients: Client[] = [];
  client: Client = new Client();
  client2: Client = new Client();
  client3: Client = new Client();
  client4: Client = new Client();

  pet1: Pets = new Pets();
  pet2: Pets = new Pets();



  router = inject(Router);


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

  findAll() {
    this.client.id = 1;
    this.client.name = "João teste";
    this.client.city = "Diadema";
    this.client.phone = "111111";
    this.client.instagram = "@teste1"

    this.client2.id = 2;
    this.client2.name = "Maria teste";
    this.client2.city = "Diadema";
    this.client2.phone = "22222";
    this.client2.instagram = "@teste2"

    this.client3.id = 3;
    this.client3.name = "Ana teste";
    this.client3.city = "Diadema";
    this.client3.phone = "33333";
    this.client3.instagram = "@teste3"

    this.client4.id = 4;
    this.client4.name = "Pedro teste";
    this.client4.city = "Diadema";
    this.client4.phone = "444444";
    this.client4.instagram = "@teste4"

    this.pet1.name = "Totó";
    this.pet1.sex = "FEMININO";
    this.pet1.additionalInformation = "";
    this.pet1.qrcode = "";
    this.pet1.photo = "img/url";
    this.pet1.wanted = false;
    this.pet1.petType = "DOG";
    this.pet1.client = this.client;

    this.pet2.name = "Banzé";
    this.pet2.sex = "MASCULINO";
    this.pet2.additionalInformation = "Agressivo";
    this.pet2.qrcode = "";
    this.pet2.photo = "img/url";
    this.pet2.wanted = false;
    this.pet2.petType = "DOG";
    this.pet2.client = this.client;

    this.client.pets.push(this.pet1);
    this.client.pets.push(this.pet2);

    this.clients.push(this.client);
    this.clients.push(this.client2);
    this.clients.push(this.client3);
    this.clients.push(this.client4);
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


