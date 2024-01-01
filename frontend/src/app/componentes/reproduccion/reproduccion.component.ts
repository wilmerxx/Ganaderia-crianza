import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ReproduccionService} from "../../service/reproduccion.service";
import {Reproduccion} from "../../models/reproduccion.model";
import {NgForm} from "@angular/forms";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reproduccion',
  templateUrl: './reproduccion.component.html',
  styleUrls: ['./reproduccion.component.css']
})
export class ReproduccionComponent implements OnInit {


  editingRow: number | null = null;

  @ViewChild('exampleModal') exampleModal!: ElementRef;

  validador: boolean;

  constructor(public reproduccionService: ReproduccionService){
    this.validador = false;
  }

  ngOnInit(): void {
    this.getReproduccion();
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
  getReproduccion(){
    this.reproduccionService.getReproduccion().subscribe((res) =>{
      this.reproduccionService.reproducciones = res as Reproduccion[];
      console.log(res);
    })
  }
  crearReproduccion(from: NgForm){
    console.log(from.value);
    this.reproduccionService.postReproduccion(from.value).subscribe((res) => {
      console.log(res);
      this.getReproduccion();
    });
  }

  deleteReproduccion(reproduccion_id: string) {
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
        this.reproduccionService.deleteReproduccion(reproduccion_id).subscribe(
          () => {
            this. getReproduccion();
            Swal.fire('Eliminado!', 'Registro eliminado', 'success');
          },
          (error) => {
            console.error('Error al eliminar', error);
            // Manejar el error según sea necesario
          }
        );
      }
    });
  }

}
