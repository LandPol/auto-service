import { Routes } from '@angular/router';
import {ClientListComponent} from './components/client-list/client-list.component';
import {ClientEditComponent} from './components/client-edit/client-edit.component';
import {ClientDetailsComponent} from './components/client-details/client-details.component';
import {CarEditComponent} from './components/car-edit/car-edit.component';
import {CarAddComponent} from './components/car-add/car-add.component';
import {CarDetailsComponent} from './components/car-details/car-details.component';

export const routes: Routes = [
  {path: '', redirectTo: 'clients', pathMatch: 'full'},
  {path: 'clients', component: ClientListComponent},
  {path: 'clients/edit/:id', component: ClientEditComponent},
  {path: 'clients/:clientId/cars', component: ClientDetailsComponent},
  {path: 'clients/:clientId/cars/add', component: CarAddComponent},
  {path: 'clients/:clientId/cars/:carId/edit', component: CarEditComponent},
  {path: 'clients/:clientId/cars/:carId', component: CarDetailsComponent}
];
