import { Component, ViewEncapsulation } from '@angular/core';
import { publicFooterComponent } from '../public-footer/public-footer.component';
import { publicMenuComponent } from '../public-menu/public-menu.component';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [publicFooterComponent, publicMenuComponent],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css',
  encapsulation: ViewEncapsulation.None
})
export class ContactComponent {

}
