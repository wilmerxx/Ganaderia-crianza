import { Component, ElementRef, ViewChild } from '@angular/core';
import {NgForm} from "@angular/forms";
import {Medicina} from "../../models/medicina.model";
import {MedicinaService} from "../../medicina.service";
import {Ganado} from "../../models/ganado";

@Component({
  selector: 'app-medicina',
  templateUrl: './medicina.component.html',
  styleUrls: ['./medicina.component.css']
})
export class MedicinaComponent {
  editingRow: number | null = null;
  @ViewChild('exampleModal') exampleModal!: ElementRef;

  validador: boolean;
  constructor(protected medicinaService: MedicinaService) {
    this.validador = false;
  }

  openModal() {
    if (this.exampleModal) {
      const modalElement = this.exampleModal.nativeElement;
      modalElement.classList.add('show');
      modalElement.style.display = 'block';  // Asegúrate de que el modal se muestre
    }
  }

  closeModal() {
    if (this.exampleModal) {
      const modalElement = this.exampleModal.nativeElement;
      modalElement.classList.remove('show');
      modalElement.style.display = 'none';  // Asegúrate de que el modal se oculte
    }
  }

  // Método para activar el modo de edición
  startEditing(rowId: number) {
    this.editingRow = rowId;
  }

  stopEditing() {
    this.editingRow = null;
  }


  // Método para determinar si la fila actual está en modo de edición
  isEditing(rowId: number): boolean {
    return this.editingRow === rowId;
  }

  crearGanado(from: NgForm){
    console.log(from.value);
    this.medicinaService.postMedicina(from.value).subscribe((res) => {
      console.log(res);
      this.getGanados();
    });
  }

  getGanados() {
    this.medicinaService.getMedicna().subscribe((res) =>{
      this.medicinaService.medicina = res as Medicina[];
      console.log(res);
    })
  }


}
