import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Client} from '../models/client.model';
import {ClientService} from '../services/client.service';

@Component({
  selector: "app-client-list",
  standalone: true,
  imports: [CommonModule],
  templateUrl: "client-list.component.html",
  styleUrl: "client-list.component.css"
})

export class ClientListComponent implements OnInit {
  clients: Client[] = [];
  errorMessage: string = "";

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
}
