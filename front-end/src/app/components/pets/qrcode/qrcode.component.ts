import { Component, inject } from '@angular/core';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { SideMenuComponent } from '../../layouts/side-menu/side-menu.component';
import Swal from 'sweetalert2';
import { Pets } from '../../../models/pets';
import { PetService } from '../../../services/pet.service';
import { FormsModule } from '@angular/forms';
import { QRCodeModule } from 'angularx-qrcode';
import { SafeValue } from '@angular/platform-browser';
import { Client } from '../../../models/client';

@Component({
  selector: 'app-qrcode',
  standalone: true,
  imports: [MdbFormsModule, SideMenuComponent, FormsModule, QRCodeModule],
  templateUrl: './qrcode.component.html',
  styleUrl: './qrcode.component.css'
})
export class QrcodeComponent {

  searchOption = "Código";
  value!: string;

  pet!: Pets;
  petService = inject(PetService);


  qrcodeView: boolean = false;
  qrdata!: string;
  qrCodeDownloadLink: SafeValue = '';

  constructor() {
  }

  onChange(url: SafeValue) {
    this.qrCodeDownloadLink = url;
  }

  findById(value: string) {
    if (value == null || value == "") {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha o código para realizar a busca!",
      });
    } else {
      this.petService.findById(Number.parseInt(value)).subscribe({
        next: response => {
          this.pet = response
          this.qrcodeView = true
          this.qrdata = this.pet.qrcode
          this.value = ""
        },
        error: e => {
          if (this.pet != null) {
            this.pet.client.name = ""
            this.pet.name = ""
            this.pet.id = 0
            this.value = ""
          } else {
            this.value = "";
          }
          this.qrcodeView = false;
          Swal.fire("Código não localizado!")
        },
      })
    }
  }

}

