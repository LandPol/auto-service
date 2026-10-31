import { Routes } from '@angular/router';
import {ClientListComponent} from './client-list/client-list.component';
import {ClientEditComponent} from './client-edit/client-edit.component';

export const routes: Routes = [
  {path: '', redirectTo: 'clients', pathMatch: 'full'},
  {path: 'clients', component: ClientListComponent},
  {path: 'clients/edit/:id', component: ClientEditComponent}
];
