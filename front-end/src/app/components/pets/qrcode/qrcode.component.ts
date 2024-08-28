import { Component, inject } from '@angular/core';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { SideMenuComponent } from '../../layouts/side-menu/side-menu.component';
import Swal from 'sweetalert2';
import { Pets } from '../../../models/pets';
import { PetService } from '../../../services/pet.service';
import { FormsModule } from '@angular/forms';
import { QRCodeModule } from 'angularx-qrcode';
import html2canvas from 'html2canvas';
import { SafeValue } from '@angular/platform-browser';

@Component({
  selector: 'app-qrcode',
  standalone: true,
  imports: [MdbFormsModule, SideMenuComponent, FormsModule, QRCodeModule],
  templateUrl: './qrcode.component.html',
  styleUrl: './qrcode.component.css'
})
export class QrcodeComponent {

  idPet!: number;
  pet!: Pets;
  petService = inject(PetService);

  qrcodeView: boolean = false;
  qrdata!: string;
  qrCodeDownloadLink: SafeValue = '';



  onChange(url: SafeValue) {
    this.qrCodeDownloadLink = url;
  }

  findPetById(id: number) {
    if (id == null) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha o campo para realizar a busca!",
      });
    } else {
      this.petService.findById(id).subscribe({
        next: response => {
          this.pet = response,
            this.qrcodeView = true,
            this.qrdata = this.pet.qrcode;
        },
        error: e => {
          if (this.pet != null) {
            this.pet.client.name = "",
              this.pet.name = "",
              this.pet.id = 0,
              this.idPet = Number(undefined);
          } else {
            this.idPet = Number(undefined);
          }
          this.qrcodeView = false;
          Swal.fire("Código não localizado!")
        },
      })
    }
  }

}
