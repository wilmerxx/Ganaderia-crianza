import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {GanadoService} from '../../service/ganado.service';
import {Ganado} from "../../models/ganado";
import { NgForm } from '@angular/forms';
import {filter} from "rxjs/operators";



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
  ganadosVaca: Ganado[] = [];
  ganadosToro: Ganado[] = [];
  ngOnInit(): void {
    this.getGanados();
    this.ganado;
    this.getGanadoVaca();
    this.getGanadoToro();
  }

  //obtener ganado por id
  buscarGanadoID(id: string){
    return this.ganadoService.getGanadoID(id).subscribe((res) =>{
      this.ganadoService.selectedGanado = res as Ganado;
    });
  }

  crearGanado(from: NgForm){
    console.log(from.value);
    //cambiar el formatoto de la fecha de nacimiento a dd-mm-yyyy
    from.value.fechaNacimiento = this.formatearFecha(from.value.fechaNacimiento);
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
      for(let i = 0; i < res.length; i++){
        this.ganadoService.ganados[i] = res[i];
        this.ganadoService.getGanadoID(res[i].madre_id??'').subscribe((res2) =>{
          this.ganadoService.ganados[i].nombreMadre = res2.nombre_ganado;
        });
        this.ganadoService.getGanadoID(res[i].padre_id??'').subscribe((res3) =>{
          this.ganadoService.ganados[i].nombrePadre = res3.nombre_ganado;
        });
      }
      console.log(res);
    })
  }

  // Método para determinar si la fila actual está en modo de edición
  putGanado(form: NgForm){
    console.log(form.value);
    //cambiar el formatoto de la fecha de nacimiento a dd-mm-yyyy
    form.value.fechaNacimiento = this.formatearFecha(form.value.fechaNacimiento);
    this.ganadoService.putGanado(form.value).subscribe((res) => {
      console.log(res);
      this.getGanados();
      this.closeModalEdit();
    });
    window.location.reload();
    console.log(form.value);
  }


  //modal de guardar
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

  //modal de editar
  @ViewChild('exampleModalEdit') exampleModalEdit!: ElementRef;
  closeModalEdit() {
    if (this.exampleModalEdit) {
      const modalElement = this.exampleModalEdit.nativeElement;
      modalElement.classList.remove('show');
      modalElement.style.display = 'none';
    }
  }

  openModalEdit(ganado: any) {
    if (this.exampleModalEdit) {
      const modalElement = this.exampleModalEdit.nativeElement;
      modalElement.classList.add('show');
      modalElement.style.display = 'block';
      this.ganadoService.getGanadoID(ganado.ganado_id).subscribe(res =>{
        this.ganado = res;
        //cambiar el formatoto de la fecha de nacimiento a dd-mm-yyyy
        this.ganado.fechaNacimiento = this.formatearFecha(ganado.fechaNacimiento);
        console.log("Funcion OpenModalEdit");
        console.log(this.ganado);
      });
    }
  }

  deleteGanado(ganado_id: string | undefined) {
    if (ganado_id) {
      this.ganadoService.deleteGanado(ganado_id).subscribe((res) => { });
      this.getGanados();
      window.location.reload();
    }
  }

  //lista de ganado tipo vaca
  getGanadoVaca() {
    //filtrar por tipo vaca
    this.ganadoService.getGanadosTipo('Vaca').subscribe((res) =>{
      this.ganadosVaca = res as Ganado[];
      console.log(res);
    });
  }
  getGanadoToro() {
    //filtrar por tipo vaca
    this.ganadoService.getGanadosTipo('Toro').subscribe((res) =>{
      this.ganadosToro = res as Ganado[];
      console.log(res);
    });
  }
  formatearFecha(fecha: string){
    let fechaNacimiento = fecha.split('-');
    let fechaNacimiento2 = fechaNacimiento[2] + '-' + fechaNacimiento[1] + '-' + fechaNacimiento[0];
    return fechaNacimiento2;
  }
}

