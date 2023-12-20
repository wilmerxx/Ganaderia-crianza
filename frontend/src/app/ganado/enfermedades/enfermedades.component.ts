import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {GanadoService} from "../../service/ganado.service";
import {Ganado} from "../../models/ganado";
import {EnfermedadesService} from "../../enfermedades.service";
import {NgForm} from "@angular/forms";
import {Enfermedad} from "../../models/enfermedades.model";
import Swal from "sweetalert2";

@Component({
  selector: 'app-enfermedades',
  templateUrl: './enfermedades.component.html',
  styleUrls: ['./enfermedades.component.css']
})
export class EnfermedadesComponent implements OnInit {

  validador: boolean;
  constructor(protected enfermedadesService: EnfermedadesService) {
    this.validador = false;
  }
  ganado: Ganado = new Ganado();
  searchText = ''
  editingRow: number | null = null;
  ngOnInit(): void {
    this.getEnfermedad()
  }

  crearEnfermedad(from: NgForm){
    console.log(from.value);
    this.enfermedadesService.postEnfermedad(from.value).subscribe((res) => {
      console.log(res);
      this.getEnfermedad();
    });
  }
  getEnfermedad() {
    this.enfermedadesService.getEnfermedad().subscribe((res) =>{
      this.enfermedadesService.enfermedades = res as Enfermedad[];
      console.log(res);
    })
  }

  isEditing(rowId: number) {
    return this.editingRow === rowId;
  }

  startEditing(rowId: number) {
    this.editingRow = rowId;
  }

  stopEditing() {
    this.editingRow = null;
  }

  closeModal() {
    if (this.exampleModal) {
      const modalElement = this.exampleModal.nativeElement;
      modalElement.classList.remove('show');
      modalElement.style.display = 'none';
    }
  }
  @ViewChild('exampleModal') exampleModal!: ElementRef;
  openModal() {
    if (this.exampleModal) {
      const modalElement = this.exampleModal.nativeElement;
      modalElement.classList.add('show');
      modalElement.style.display = 'block';
    }
  }

  protected readonly onsubmit = onsubmit;
  delete(control_id: string) {
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'Este registro se eliminará completamente',
      position: 'top',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, deseo eliminarlo!',
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.isConfirmed) {
        this.enfermedadesService.deleteEnfermedades(control_id).subscribe(
          () => {
            this.getEnfermedad();
            Swal.fire('Eliminado!', 'Registro eliminado', 'success');
          },
          (error) => {
            console.error('Error al eliminar el área', error);
            // Manejar el error según sea necesario
          }
        );
      }
    });
  }

}
