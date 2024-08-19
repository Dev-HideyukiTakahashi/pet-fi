import { Routes } from '@angular/router';
import { LoginComponent } from './components/layouts/login/login.component';
import { HomeComponent } from './components/layouts/home/home.component';
import { ClientsListComponent } from './components/components/clients/clients-list/clients-list.component';
import { ClientDetailsComponent } from './components/components/clients/client-details/client-details.component';
import { ClientSearchComponent } from './components/components/clients/client-search/client-search.component';

export const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: "login", component: LoginComponent },
  {
    path: "admin/home", component: HomeComponent,
    children: [
      { path: "clients", component: ClientsListComponent },
      { path: "clients/new", component: ClientDetailsComponent },
      { path: "clients/edit/:id", component: ClientDetailsComponent },
      { path: "clients/:id", component: ClientSearchComponent }
    ]
  }
];
