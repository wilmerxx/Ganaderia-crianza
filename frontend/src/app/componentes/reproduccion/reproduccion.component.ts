import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ReproduccionService} from "../../service/reproduccion.service";
import {Reproduccion} from "../../models/reproduccion.model";
import {FormControl, FormBuilder, FormGroup, NgForm, Validators } from "@angular/forms";
import Swal from 'sweetalert2';
import {Ganado} from "../../models/ganado";
import {GanadoService} from "../../service/ganado.service";

@Component({
  selector: 'app-reproduccion',
  templateUrl: './reproduccion.component.html',
  styleUrls: ['./reproduccion.component.css']
})
export class ReproduccionComponent implements OnInit {

  textoBusqueda: string = '';
  form!: FormGroup;


  constructor(public reproduccionService: ReproduccionService, public ganadoService: GanadoService,         private formBuilder: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.getReproduccion();
    this.formularioNuevoReproduccion();
  }

  buscarGanado() {
    if (this.textoBusqueda !== '') {
      this.ganadoService.busquedaGanado(this.textoBusqueda).subscribe((res) => {
        this.ganadoService.ganados = res as Ganado[];
      });
    }
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


  getReproduccion(){
    this.reproduccionService.getReproduccion().subscribe((res) =>{
      this.reproduccionService.reproducciones = res as Reproduccion[];
      console.log(res);
    })
  }
  private formularioNuevoReproduccion() {
    this.form = this.formBuilder.group({
      ganado: new FormControl('', [Validators.required]),
      fecha_parto: new FormControl('', [Validators.required]),
      estado_parto: new FormControl('', [Validators.required]),
      observaciones: new FormControl('', [Validators.required]),
      numero_crias: new FormControl('', [Validators.required])
    });
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
