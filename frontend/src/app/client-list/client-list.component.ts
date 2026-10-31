import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Client} from '../models/client.model';
import {ClientService} from '../services/client.service';

@Component({
  selector: "app-client-list",
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: "client-list.component.html",
  styleUrl: "client-list.component.css"
})

export class ClientListComponent implements OnInit {
  clients: Client[] = [];
  errorMessage: string = "";

  newClientName: string = "";
  newClientAge: number | null = null;

  constructor(private clientService: ClientService) {

  }

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.clientService.getClients().subscribe({
      next: (data) => {
        //console.log("Backend returns: ", data);
        this.clients = data;
      },
      error: (err) => {
        this.errorMessage = "Failed to download list of clients";
        console.error(err);
      }
    });
  }

  onDelete(id: string): void {
    this.clientService.deleteClientById(id).subscribe({
      next: () => {
        this.loadClients();
        window.location.reload();
      },
      error: (err) => {
        this.errorMessage = `Failed to delete client ${id}`;
        console.error(err);
      }
    })
  }

  addClient(): void {
    if (!this.newClientName.trim() || !this.newClientAge) {
      this.errorMessage = "Please provide both name and age"
      return;
    }
    this.clientService.createClient({name: this.newClientName, age: this.newClientAge}).subscribe({
      next: () => {
        this.newClientName = "";
        this.newClientAge = null;
        this.loadClients();
        window.location.reload();
      },
      error: (err) => {
        this.errorMessage = "Filed to add new client"
        console.error(err);
      }
    })
  }
}
