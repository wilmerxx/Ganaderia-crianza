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
  ganado: Ganado = new Ganado();
  searchText = ''
  editingRow: number | null = null;
  ngOnInit(): void {
   this.getGanados()
  }

  /*
  "codigo": "1",
        "nombre_ganado": "1",
        "raza": "1",
        "peso": 345.0,
        "sexo": "1",
        "fechaNacimiento": "20-12-2023",
        "tipo": "1",
        "madre_id": null,
        "padre_id": null,
        "madre": null,
        "padre": null,
        "estado": "1",
   */


  ngSubmit() {
   // this.ganadoService.postGanado(this.ganadoService.selectedGanado).subscribe((res) => {

    console.log(this.ganadoService.selectedGanado);
      //this.getGanados();
    //});
  }
  crearGanado(from: NgForm){
    console.log(from.value);
    this.ganadoService.postGanado(from.value).subscribe((res) => {
      console.log(res);
      this.getGanados();
    });
  }

  getGanados() {
    this.ganadoService.getGanados().subscribe((res) =>{
      this.ganadoService.ganados = res as Ganado[];
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

}

