import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Client } from '../../models/client.model';
import { CarService } from '../../services/car.service';
import {Car} from '../../models/car.model';

@Component({
  selector: "app-car-details",
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: "car-details.component.html",
  styleUrl: "car-details.component.css"
})

export class CarDetailsComponent implements OnInit {
  clientId: string = "";
  client: Client | null = null;
  car: Car | null = null;
  errorMessage: string = "";

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private carService: CarService,
    private changeDetectorRef: ChangeDetectorRef
  ) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.clientId = params.get('clientId') || '';
      const carId = params.get('carId');
      if (this.clientId && carId) {
        this.loadCarDetails(this.clientId, carId);
      } else {
        this.errorMessage = "Invalid parameters";
      }
    });
  }

  loadCarDetails(clientId: string, carId: string): void {
    this.carService.getCarById(clientId, carId).subscribe({
      next: (data) => {
        this.car = {...data};
        this.changeDetectorRef.detectChanges();
      },
      error: (err) => {
        this.errorMessage = "Filed to load client details"
        console.error(err);
      }
    })
  }

  onBack(): void {
    this.router.navigate([`/clients/${this.clientId}/cars`]);
  }
}
