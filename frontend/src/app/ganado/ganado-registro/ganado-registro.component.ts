import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {GanadoService} from '../../service/ganado.service';
import {Ganado} from "../../models/ganado";
import { NgForm } from '@angular/forms';


@Component({
  selector: 'ganado-registro',
  templateUrl: './ganado-registro.component.html',
  styleUrls: ['./ganado-registro.component.css']
})
export class GanadoRegistroComponent implements OnInit{
  validador: boolean;
  constructor(protected ganadoService: GanadoService) {
    this.validador = false;
  }
  ganado_id: string = '';
  ganado: Ganado = new Ganado();
  searchText = ''
  editingRow: number | null = null;
  ngOnInit(): void {

   this.getGanados();
   ganado: this.buscarGanadoID(this.ganado_id);
  }


  //obtener ganado por id
  buscarGanadoID(id: string){
    this.ganadoService.getGanadoID(id).subscribe((res) =>{
      this.ganadoService.selectedGanado = res as Ganado;
      console.log(res);
    }
    )
  }

  crearGanado(from: NgForm){
    console.log(from.value);
    this.ganadoService.postGanado(from.value).subscribe((res) => {
      console.log(res);
      this.getGanados();
      this.closeModal();
      this.limpiarFormulario(from);
    });
  }

  limpiarFormulario(form: NgForm){
    form.reset();
  }

  getGanados() {
    this.ganadoService.getGanados().subscribe((res) =>{
      this.ganadoService.ganados = res as Ganado[];
      console.log(res);
    })
  }

  // Método para determinar si la fila actual está en modo de edición
  putGanado(form: NgForm){
    console.log(form.value);
    this.ganadoService.putGanado(form.value).subscribe((res) => {
      console.log(res);
      this.getGanados();
      this.closeModal();
      this.limpiarFormulario(form);
    });
  }


  isEditing(rowId: number) {
    return this.editingRow === rowId;
  }

  startEditing(rowId: number) {
    this.editingRow = (rowId);
    console.log(rowId);
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

  closeModalEdit() {

  }

  deleteGanado(ganado_id: string | undefined) {
    if (ganado_id) {
      this.ganadoService.deleteGanado(ganado_id).subscribe((res) => {
        console.log(res);
        this.getGanados();
        //actualizar la pagina

      });
    }

  }
}

