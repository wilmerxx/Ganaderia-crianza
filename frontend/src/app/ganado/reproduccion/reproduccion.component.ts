import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';


@Component({
  selector: 'app-reproduccion',
  templateUrl: './reproduccion.component.html',
  styleUrls: ['./reproduccion.component.css']
})
export class ReproduccionComponent implements OnInit {


  ngOnInit(): void {

  }

  areaData: any[] = [
    { nombre: 'La palmera',tipo_terreno:'plano', tipo_pasto: 'Gramalote', superficie: '3 hectareas',total_ganado:'23' },

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
