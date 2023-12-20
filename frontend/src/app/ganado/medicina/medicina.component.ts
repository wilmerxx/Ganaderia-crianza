import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import {MedicinaService} from "../../medicina.service";
import {Medicina} from "../../models/medicina.model";


@Component({
  selector: 'app-medicina',
  templateUrl: './medicina.component.html',
  styleUrls: ['./medicina.component.css']
})
export class MedicinaComponent implements OnInit {

  validador: boolean;
  constructor(public medicinaService: MedicinaService) {
    this.validador = false;
  }
  ngOnInit(): void {
    this.getMedicinas();
  }

  editingRow: number | null = null;

  @ViewChild('exampleModal') exampleModal!: ElementRef;
  getMedicinas(){
    this.medicinaService.getMedicinas().subscribe((res) =>{
      this.medicinaService.medicina = res as Medicina[];
      console.log(res);
    })
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

}
