import { Component, ViewChild } from '@angular/core';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { ClientMenuComponent } from '../../components/clients/client-menu/client-menu.component';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [MdbCollapseModule, ClientMenuComponent],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

}
