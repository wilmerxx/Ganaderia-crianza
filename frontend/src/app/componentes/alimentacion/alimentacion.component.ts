import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AlimentacionService} from "../../service/alimentacion.service";
import {Alimentacion} from "../../models/alimentacion.model";

@Component({
  selector: 'app-alimentacion',
  templateUrl: './alimentacion.component.html',
  styleUrls: ['./alimentacion.component.css']
})
export class AlimentacionComponent implements OnInit {

  validador: boolean;

  constructor(public alimentacionService: AlimentacionService) {
    this.validador = false;
  }
  ngOnInit(): void {
    this.getAlimentacion();
  }

  editingRow: number | null = null;

  @ViewChild('exampleModal') exampleModal!: ElementRef;
  getAlimentacion(){
    this.alimentacionService.getAlimentacion().subscribe((res) =>{
      this.alimentacionService.alimentacion = res as Alimentacion[];
      console.log(res);
    })
  }

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
