import { Routes } from '@angular/router';
import { LoginComponent } from './components/layouts/login/login.component';
import { HomeComponent } from './components/layouts/home/home.component';
import { ClientsListComponent } from './components/clients/clients-list/clients-list.component';
import { ClientDetailsComponent } from './components/clients/client-details/client-details.component';
import { ClientSearchComponent } from './components/clients/client-search/client-search.component';
import { PetDetailsComponent } from './components/pets/pet-details/pet-details.component';
import { PetSearchComponent } from './components/pets/pet-search/pet-search.component';
import { HelpComponent } from './components/layouts/help/help.component';
import { QrcodeComponent } from './components/pets/qrcode/qrcode.component';
import { loginGuard } from './auth/login-guard';
import { PublicPetListComponent } from './components/public/public-pet-list/public-pet-list.component';
import { PublicPetDetailsComponent } from './components/public/public-pet-details/public-pet-details.component';
import { ContactComponent } from './components/public/contact/contact.component';

export const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: "home", component: PublicPetListComponent },
  { path: "home/pet/:id", component: PublicPetDetailsComponent },
  { path: "home/contact", component: ContactComponent },
  { path: "login", component: LoginComponent },
  {
    path: "admin/home", component: HomeComponent, canActivate: [loginGuard],
    children: [
      { path: "help", component: HelpComponent },
      { path: "clients", component: ClientsListComponent },
      { path: "client/new", component: ClientDetailsComponent },
      { path: "clients/edit/:id", component: ClientDetailsComponent },
      { path: "client", component: ClientSearchComponent },
      { path: "pet/new", component: PetDetailsComponent },
      { path: "pet/edit/:id", component: PetDetailsComponent },
      { path: "pet", component: PetSearchComponent },
      { path: "pet/qrcode", component: QrcodeComponent },
    ]
  },

];
