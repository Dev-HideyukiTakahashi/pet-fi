
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';

@Component({
  selector: 'app-client-menu',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './client-menu.component.html',
  styleUrl: './client-menu.component.css'
})
export class ClientMenuComponent {

  phone: string = "";
  name: string = "";
  city: string = "";

  findClient() {
    console.log(this.phone);
    if (this.phone == "" && this.name == "" && this.city == "") {
      alert("Preencha pelo menos um campo!");
    }
  }
}
