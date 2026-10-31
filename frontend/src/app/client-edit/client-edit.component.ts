import {Component, OnInit, ChangeDetectorRef} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Client} from '../models/client.model';
import {ClientService} from '../services/client.service';

@Component({
  selector: "app-client-edit",
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './client-edit.component.html',
  styleUrl: './client-edit.component.css'
})

export class ClientEditComponent implements OnInit {
  clientId: string = "";
  client: Client = {id: "", name: "", age: null};
  errorMessage: string = "";

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService,
    private changeDetectorRef: ChangeDetectorRef
  ) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam) {
        this.clientId = idParam;
        this.loadClientData(this.clientId);
      } else {
        this.errorMessage = "Invalid client Id"
      }
    });
  }

  loadClientData(id: string): void {
    this.clientService.getClientById(id).subscribe({
      next: (data) => {
        this.client = {...data};
        this.changeDetectorRef.detectChanges();
      },
      error: (err) => {
        this.errorMessage = "Filed to load client details for editing"
        console.error(err);
      }
    })
  }

  onSave(): void {
    if (!this.client.name.trim()) {
      this.errorMessage = "Client name cannot be empty"
      return;
    }

    if (this.client.age == null || this.client.age < 0) {
      this.errorMessage = "Client age cannot be negative or null";
      return;
    }

    this.clientService.updateClientById(this.client.id, {name: this.client.name, age: this.client.age}).subscribe({
      next: () => {
        this.router.navigate(['/clients']);
      },
      error: (err) => {
        this.errorMessage = "Filed to update client";
        console.error(err);
      }
    })
  }

  onCancel(): void {
    this.router.navigate(['/clients']);
  }
}
