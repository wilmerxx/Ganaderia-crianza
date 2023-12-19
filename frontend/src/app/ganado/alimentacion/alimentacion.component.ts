import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'app-alimentacion',
  templateUrl: './alimentacion.component.html',
  styleUrls: ['./alimentacion.component.css']
})
export class AlimentacionComponent{
  alimentoData: any[] = [
    { tipoGanado: 'Vaca',nombre:'lola', suplemento: 'Pasto', cantidad: '10kg', fecha: '2023-01-01' },
    { tipoGanado: 'Toro',nombre:'lola', suplemento: 'Pasto', cantidad: '10kg', fecha: '2023-02-01' },
    { tipoGanado: 'Ternero',nombre:'lola', suplemento: 'Pasto', cantidad: '10kg', fecha: '2023-03-01' }
  ];
  editingRow: number | null = null;

  @ViewChild('exampleModal') exampleModal!: ElementRef;

  constructor() {}

  openModal() {
    if (this.exampleModal) {
      const modalElement = this.exampleModal.nativeElement;
      modalElement.classList.add('show');
      modalElement.style.display = 'block';
    }
  }
  closeModal() {
    if (this.exampleModal) {
      const modalElement = this.exampleModal.nativeElement;
      modalElement.classList.remove('show');
      modalElement.style.display = 'none';
    }
  }
  startEditing(rowId: number) {
    this.editingRow = rowId;
  }

  stopEditing() {
    this.editingRow = null;
  }

  isEditing(rowId: number): boolean {
    return this.editingRow === rowId;
  }

}
