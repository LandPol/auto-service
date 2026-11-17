import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Client } from '../../models/client.model';
import { CarCollection } from '../../models/car-collection.model';
import { ClientService } from '../../services/client.service';
import { CarService } from '../../services/car.service';

@Component({
  selector: "app-client-details",
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: "client-details.component.html",
  styleUrl: "client-details.component.css"
})

export class ClientDetailsComponent implements OnInit {
  clientId: string = "";
  client: Client | null = null;
  cars: CarCollection[] = [];
  errorMessage: string = "";

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService,
    private carService: CarService,
    private changeDetectorRef: ChangeDetectorRef
  ) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('clientId');
      if (idParam) {
        this.clientId = idParam;
        this.loadClientData(this.clientId);
        this.loadClientCars(this.clientId);
      } else {
        this.errorMessage = "Invalid client Id";
      }
    });
  }

  loadClientData(clientId: string): void {
    this.clientService.getClientById(clientId).subscribe({
      next: (data) => {
        this.client = {...data};
        this.changeDetectorRef.detectChanges();
      },
      error: (err) => {
        this.errorMessage = "Filed to load client details"
        console.error(err);
      }
    })
  }

  loadClientCars(clientId: string): void {
    this.carService.getCarsByClientId(clientId).subscribe({
      next: (data) => {
        this.cars = {...data};
        this.cars = Array.isArray(data) ? data : [];
        this.changeDetectorRef.detectChanges();
      },
      error: (err) => {
        this.errorMessage = "Filed to load client cars";
        console.error(err);
        this.cars = [];
        this.changeDetectorRef.detectChanges();
      }
    })
  }

  onDelete(clientId: string, carId: string): void {
    this.carService.deleteCarById(clientId, carId).subscribe({
      next: () => {
        this.loadClientData(clientId);
        this.loadClientCars(clientId);
      },
      error: (err) => {
        this.errorMessage = `Failed to delete client ${clientId} car ${carId}`;
        console.error(err);
      }
    })
  }

  onBack(): void {
    this.router.navigate(['/clients']);
  }
}
