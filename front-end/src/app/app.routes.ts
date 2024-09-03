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
import { PetListComponent } from './components/public/pet-list/pet-list.component';

export const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full" },
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
  { path: "home", component: PetListComponent },
  { path: "home/pet/:id", component: PetSearchComponent },
];
