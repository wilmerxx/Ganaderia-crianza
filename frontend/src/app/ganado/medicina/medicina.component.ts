import { Component, ElementRef, ViewChild } from '@angular/core';


@Component({
  selector: 'app-medicina',
  templateUrl: './medicina.component.html',
  styleUrls: ['./medicina.component.css']
})
export class MedicinaComponent {
  medicinaData: any[] = [
    { tipoGanado: 'Vaca', sintoma: 'Fiebre', diagnostico: 'Resfriado', tratamiento: 'Descanso', fechaVacuna: '2023-01-01' },
    { tipoGanado: 'Toro', sintoma: 'Dolor de cabeza', diagnostico: 'Migraña', tratamiento: 'Analgésicos', fechaVacuna: '2023-02-01' },
    { tipoGanado: 'Ternero', sintoma: 'Dolor de estómago', diagnostico: 'Indigestión', tratamiento: 'Ayuno', fechaVacuna: '2023-03-01' }
    // Agrega más datos de prueba según sea necesario
  ];
  editingRow: number | null = null;

  @ViewChild('exampleModal') exampleModal!: ElementRef;

  constructor() {}

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
