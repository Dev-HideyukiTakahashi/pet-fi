import { Component, inject } from '@angular/core';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { SideMenuComponent } from '../../layouts/side-menu/side-menu.component';
import Swal from 'sweetalert2';
import { Pets } from '../../../models/pets';
import { PetService } from '../../../services/pet.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-qrcode',
  standalone: true,
  imports: [MdbFormsModule, SideMenuComponent, FormsModule],
  templateUrl: './qrcode.component.html',
  styleUrl: './qrcode.component.css'
})
export class QrcodeComponent {

  idPet!: number;
  pet!: Pets;
  petService = inject(PetService);

  findPetById(id: number) {
    if (id == null) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Preencha o campo para realizar a busca!",
      });
    } else {
      this.pet = new Pets();
      this.petService.findById(id).subscribe({
        next: response => this.pet = response,
        error: e => { Swal.fire("Código não localizado!"), this.idPet = Number(undefined) },
      })
    }
  }
}
