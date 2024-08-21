import { Component, inject, Input, input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import Swal from 'sweetalert2';
import { Client } from '../../../../models/client';
import { FormsModule } from '@angular/forms';
import { MdbValidationModule } from 'mdb-angular-ui-kit/validation';

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
    this.client = clientReturned;
  }

  findClient() {
    this.router.navigate(["admin/home/client"]);
  }

  newClient() {
    this.router.navigate(["admin/home/clients/new"]);
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
        this.router.navigate(["admin/home/menu/clients"], { state: { updatedClient: this.client } })
      } else {

        //APAGAR
        this.client.id = 555;

        Swal.fire({
          title: 'Sucesso!',
          text: 'Novo cadastro realizado.',
          icon: 'success',
          confirmButtonText: 'Ok'
        })
        this.router.navigate(["admin/home/menu/clients"], { state: { newClient: this.client } })
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
