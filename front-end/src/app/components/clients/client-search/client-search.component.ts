import { Component, inject } from '@angular/core';
import { Client } from '../../../models/client';
import { Router, RouterLink } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { FormsModule } from '@angular/forms';
import { ClientService } from '../../../services/client.service';
import Swal from 'sweetalert2';
import { SideMenuComponent } from '../../layouts/side-menu/side-menu.component';
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
  totalPages!: number[];

  idClient!: number;
  phoneClient!: string;


  searchOption = "Selecione";
  value!: string;

  search() {
    if (this.searchOption == "Selecione" || this.searchOption == "") {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Selecione o o filtro de busca!",
      });
    }
    if (this.searchOption == "name") { this.findClientByName(this.value) }
    if (this.searchOption == "instagram") { this.findClientByInstagram(this.value) }
    if (this.searchOption == "phone") { this.findClientByPhone(this.value) }
  }

  findPaged(page: number) {
    if (this.searchOption == "name") {
      this.clientService.findByNamePaged(this.value, page).subscribe({
        next: response => this.clients = response.content,
      })
    }
  }

  findClientByPhone(value: string) {
    let phone = Number.parseInt(value);
    if (phone == null) { this.emptyFieldError() }
    if (isNaN(phone)) { Swal.fire("Telefone apenas números!") }
    else {
      this.clients = [];
      this.clientService.findByPhone(phone).subscribe({
        next: response => {
          if (response.content.length > 0) { this.clients = response.content }
          else { Swal.fire("Telefone não localizado!"), this.value = "" }
        },
        error: e => { console.log(e.error.message) },
      });
    }
  }

  findClientByName(name: string) {
    if (name == null || name == "") { this.emptyFieldError() }
    else {
      this.clientService.findByName(name).subscribe({
        next: response => {
          if (response.content.length > 0) { this.clients = response.content, this.totalPages = new Array(response.totalPages); }
          else { Swal.fire("Nome não localizado!"), this.value = "" }
        },
        error: e => { console.log(e.error.message) },
      });
    }
  }

  findClientByInstagram(instagram: string) {
    if (instagram == null || instagram == "") { this.emptyFieldError() }
    else {
      if (instagram.startsWith("@")) {
        instagram = instagram.substring(1);
      }
      this.clientService.findByInstagram(instagram).subscribe({
        next: response => {
          if (response.content.length > 0) { this.clients = response.content }
          else { Swal.fire("Instagram não localizado!"), this.value = "" }
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

  emptyFieldError() {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "Preencha o campo para realizar a busca!",
    });
  }

}
