import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';

@Component({
  selector: 'app-pet-list',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './pet-list.component.html',
  styleUrl: './pet-list.component.css'
})
export class PetListComponent {

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
