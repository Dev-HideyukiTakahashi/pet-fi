import { Component } from '@angular/core';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';

@Component({
  selector: 'app-public-menu',
  standalone: true,
  imports: [MdbCollapseModule],
  templateUrl: './public-menu.component.html',
  styleUrl: './public-menu.component.css'
})
export class publicMenuComponent {

}
