import { Routes } from '@angular/router';
import { LoginComponent } from './components/layouts/login/login.component';
import { HomeComponent } from './components/layouts/home/home.component';
import { ClientsListComponent } from './components/components/clients/clients-list/clients-list.component';
import { ClientDetailsComponent } from './components/components/clients/client-details/client-details.component';
import { ClientSearchComponent } from './components/components/clients/client-search/client-search.component';
import { PetListComponent } from './components/components/pets/pet-list/pet-list.component';
import { PetDetailsComponent } from './components/components/pets/pet-details/pet-details.component';
import { PetSearchComponent } from './components/components/pets/pet-search/pet-search.component';
import { HelpComponent } from './components/layouts/help/help.component';

export const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: "login", component: LoginComponent },
  {
    path: "admin/home", component: HomeComponent,
    children: [
      { path: "help", component: HelpComponent },
      { path: "clients", component: ClientsListComponent },
      { path: "client/new", component: ClientDetailsComponent },
      { path: "clients/edit/:id", component: ClientDetailsComponent },
      { path: "client", component: ClientSearchComponent },
      { path: "pets", component: PetListComponent },
      { path: "pet/new", component: PetDetailsComponent },
      { path: "pet/edit/:id", component: PetDetailsComponent },
      { path: "pet", component: PetSearchComponent }
    ]
  }
];
