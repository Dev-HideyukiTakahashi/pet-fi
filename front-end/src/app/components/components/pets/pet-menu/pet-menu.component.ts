import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';

@Component({
  selector: 'app-pet-menu',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './pet-menu.component.html',
  styleUrl: './pet-menu.component.css'
})
export class PetMenuComponent {

  id!: number;
  name: string = "";
  clientName: string = "";
  selectedOption: string = "no";



  findPet() {
    if (this.id == null && this.name == "" && this.clientName == "" && this.selectedOption == "no") {
      alert("Preencha pelo menos um campo!");
    }
  }
}
