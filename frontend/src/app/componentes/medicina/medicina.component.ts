
import { Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import {MedicinaService} from "../../service/medicina.service";
import {Medicina} from "../../models/medicina.model";
import {GanadoService} from "../../service/ganado.service";
import {NgForm} from "@angular/forms";
import {Ganado} from "../../models/ganado";

@Component({
  selector: 'app-medicina',
  templateUrl: './medicina.component.html',
  styleUrls: ['./medicina.component.css']
})
export class MedicinaComponent implements OnInit {

  validador: boolean;


  constructor(public medicinaService: MedicinaService, public ganadoService: GanadoService) {
    this.validador = false;
  }
  ngOnInit(): void {
    this.getGanados();
    this.getMedicinas();
  }


  editingRow: number | null = null;
  @ViewChild('exampleModal') exampleModal!: ElementRef;

  crearMedicina(from: NgForm) {
    console.log(from.value);
    this.medicinaService.postMedicina(from.value).subscribe((res) => {
      console.log(res);
      this.getMedicinas();
      this.closeModal();
      this.limpiarFormulario(from);
    });
  }

  limpiarFormulario(from: NgForm) {
    from.resetForm();
  }



  getMedicinas(){
    this.medicinaService.getMedicinas().subscribe((res) =>{
      this.medicinaService.medicina = res as Medicina[];
      this.getGanados();
      console.log(res);
    })
  }
/*
    obtenerNombreDatos() {
        for (const medicina of this.medicinaService.medicina) {
            this.medicinaService.getGanadoNombre(medicina.ganado_id).subscribe(
                (nombreGanado) => {
                    medicina.nombre_ganado= nombreGanado;
                },
                (error) => {
                    console.error(`Error al obtener el nombre del ganado (${medicina.ganado_id}):`, error);
                }
            );
        }
    }*/
  getGanados() {
    this.ganadoService.getGanados().subscribe((res) =>{
      this.ganadoService.ganados = res as Ganado[];
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

  crearGanado(from: NgForm){
    console.log(from.value);
    this.medicinaService.postMedicina(from.value).subscribe((res) => {
      console.log(res);
      this.getGanados();
    });
  }


}
