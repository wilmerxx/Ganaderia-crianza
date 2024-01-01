import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AreaService} from "../../service/area.service";
import {Area} from "../../models/area.model";
import { HttpClient } from '@angular/common/http';
import {NgForm} from "@angular/forms";
import Swal from 'sweetalert2';


@Component({
  selector: 'app-area',
  templateUrl: './area.component.html',
  styleUrls: ['./area.component.css']
})
export class AreaComponent implements OnInit {
  validador: boolean;
  editarAreaIndex: number | null = null;

  constructor(public areaService: AreaService) {
    this.validador = false;
  }

  ngOnInit(): void {
    this.getAreas();
  }

  areaData: any[] = [
    { nombre: 'La palmera',tipo_terreno:'plano', tipo_pasto: 'Gramalote', superficie: '3 hectareas',total_ganado:'23' },

  ];

  editingRow: number | null = null;

  @ViewChild('exampleModal') exampleModal!: ElementRef;


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
     //OBTENER TODAS LAS AREAS
  getAreas(){
    this.areaService.getAreas().subscribe((res) =>{
      this.areaService.areas = res as Area[];
      console.log(res);
    })
  }
  //agregar areas
  crearArea(from: NgForm){
    console.log(from.value);
    this.areaService.postArea(from.value).subscribe((res) => {
      this.getAreas();
    });
  }


  resetForm(form: NgForm) {
    form.reset();
    Swal.fire({
      position: 'top',
      icon: 'success',
      title: 'Formulario limpiado',
      showConfirmButton: false,
      timer: 2000,
    });
  }


  deleteArea(areaId: string) {
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
        this.areaService.deleteArea(areaId).subscribe(
          () => {
            this.getAreas();
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
