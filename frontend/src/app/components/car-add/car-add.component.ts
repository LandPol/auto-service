import {Component, OnInit, ChangeDetectorRef} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Car} from '../../models/car.model';
import {CarService} from '../../services/car.service';
import {ClientService} from '../../services/client.service';
import {Client} from '../../models/client.model';

@Component({
  selector: "app-car-add",
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './car-add.component.html',
  styleUrl: './car-add.component.css'
})

export class CarAddComponent implements OnInit {
  clientId: string = "";
  car: Car = {id: "", model: "", type: "", productionYear: null, clientId: this.clientId}
  clients: Client[] = [];
  errorMessage: string = "";

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private carService: CarService,
    private clientService: ClientService,
    private changeDetectorRef: ChangeDetectorRef
  ) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const clientId = params.get('clientId');
      if (clientId) {
        this.clientId = clientId;
        this.car.clientId = clientId;
        this.loadClients();
      } else {
        this.errorMessage = "Invalid parameters"
      }
    });
  }

  loadClients(): void {
    this.clientService.getClients().subscribe({
      next: (data) => {
        this.clients = data;
        this.changeDetectorRef.detectChanges();
      },
      error: (err) => {
        this.errorMessage = "Failed to download list of clients";
        console.error(err);
      }
    });
  }

  onSave(): void {
    if (!this.car.model.trim()) {
      this.errorMessage = "Car model cannot be empty"
      return;
    }

    if (!this.car.type.trim()) {
      this.errorMessage = "Car type cannot be empty"
      return;
    }

    if (this.car.productionYear == null || this.car.productionYear < 1900 || this.car.productionYear > new Date().getFullYear()) {
      this.errorMessage = "Car production year cannot be negative or null or from the future";
      return;
    }

    this.carService.createCar(this.car.clientId, {model: this.car.model, type: this.car.type, productionYear: this.car.productionYear, clientId: this.car.clientId}).subscribe({
      next: () => {
        this.router.navigate([`/clients/${this.car.clientId}/cars`]);
      },
      error: (err) => {
        this.errorMessage = "Filed to update client";
        console.error(err);
      }
    })
  }

  onCancel(): void {
    this.router.navigate([`/clients/${this.clientId}/cars`]);
  }
}
