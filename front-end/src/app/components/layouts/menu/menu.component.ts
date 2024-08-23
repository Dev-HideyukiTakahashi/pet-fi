import { Component, ViewChild } from '@angular/core';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { ClientsListComponent } from '../../components/clients/clients-list/clients-list.component';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [MdbCollapseModule, ClientsListComponent],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

}
