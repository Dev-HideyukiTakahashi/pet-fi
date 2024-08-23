import { Component, inject, Input, input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import Swal from 'sweetalert2';
import { Client } from '../../../../models/client';
import { FormsModule } from '@angular/forms';
import { MdbValidationModule } from 'mdb-angular-ui-kit/validation';
import { Pets } from '../../../../models/pets';

@Component({
  selector: 'app-client-details',
  standalone: true,
  imports: [MdbFormsModule, FormsModule, MdbValidationModule],
  templateUrl: './client-details.component.html',
  styleUrl: './client-details.component.css'
})
export class ClientDetailsComponent {

  router = inject(Router);
  activatedRouter = inject(ActivatedRoute);
  client: Client = new Client();
  error: boolean = false;
  edit: boolean = false;
  pet1: Pets = new Pets();
  pet2: Pets = new Pets();

  constructor() {
    let id = this.activatedRouter.snapshot.params['id'];
    if (id > 0) {
      this.findById(id);
      this.edit = true;
    }
  }

  findById(id: number) {
    let clientReturned: Client = new Client();
    clientReturned.id = id;
    clientReturned.name = "teste updated";
    clientReturned.city = "Diadema"
    clientReturned.phone = "111111";
    clientReturned.instagram = "@update"

    this.pet1.id = 1;
    this.pet1.name = "Totó";
    this.pet1.sex = "FEMEA";
    this.pet1.additionalInformation = "";
    this.pet1.qrcode = "";
    this.pet1.photo = "img/url";
    this.pet1.wanted = false;
    this.pet1.petType = "DOG";
    this.pet1.client = this.client;

    this.pet2.id = 2;
    this.pet2.name = "Banzé";
    this.pet2.sex = "MACHO";
    this.pet2.additionalInformation = "Agressivo";
    this.pet2.qrcode = "";
    this.pet2.photo = "img/url";
    this.pet2.wanted = false;
    this.pet2.petType = "DOG";
    this.pet2.client = this.client;

    clientReturned.pets.push(this.pet1);
    clientReturned.pets.push(this.pet2);

    this.client = clientReturned;
  }

  findClient() {
    this.router.navigate(["admin/home/client"]);
  }

  newClient() {
    this.router.navigate(["admin/home/client/new"]);
  }

  registerClient() {
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
      }

      if (this.client.id != null) {
        Swal.fire({
          title: 'Editado com sucesso!',
          icon: 'success',
          confirmButtonText: 'Ok'
        })
        this.router.navigate(["admin/home/clients"], { state: { updatedClient: this.client } })
      } else {

        //APAGAR
        this.client.id = 555;

        Swal.fire({
          title: 'Sucesso!',
          text: 'Novo cadastro realizado.',
          icon: 'success',
          confirmButtonText: 'Ok'
        })
        this.router.navigate(["admin/home/clients"], { state: { newClient: this.client } })
      }
    }
  }


  registerPet() {
    if (this.client.id == null) {
      Swal.fire({
        title: "Atenção",
        text: "Cadastro do cliente foi salvo?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sim",
        cancelButtonText: "Não",
      }).then((result) => {
        if (result.isConfirmed) {
          console.log("registar pet")
        }
      });
    }

  }

}
