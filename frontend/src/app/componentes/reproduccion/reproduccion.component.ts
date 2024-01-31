import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ReproduccionService} from "../../service/reproduccion.service";
import {Reproduccion} from "../../models/reproduccion.model";
import {FormControl, FormBuilder, FormGroup, NgForm, Validators } from "@angular/forms";
import Swal from 'sweetalert2';
import {Ganado} from "../../models/ganado";
import {GanadoService} from "../../service/ganado.service";
import {catchError} from "rxjs/operators";
import {Medicina} from "../../models/medicina.model";
import {Observable} from "rxjs";

@Component({
  selector: 'app-reproduccion',
  templateUrl: './reproduccion.component.html',
  styleUrls: ['./reproduccion.component.css']
})
export class ReproduccionComponent implements OnInit {

  reproduccion: Reproduccion = new Reproduccion();
  form!: FormGroup;
  resultados$!: Observable<string[]>;

  constructor(
    protected reproduccionService: ReproduccionService,
    protected ganadoService: GanadoService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.formularioNuevoReproduccion();
    this.getReproduccion();
    this.getGanados();
  }

  private formularioNuevoReproduccion() {
    this.form = this.formBuilder.group({
      fecha_parto: new FormControl('' ),
      estado_parto: new FormControl(''),
      observaciones: new FormControl(''),
      numero_crias: new FormControl(''),
      ganado_id: new FormControl('', [Validators.required])
    });
  }
  guardar(event: Event) {
    event.preventDefault();
    console.log(this.form.value);
    if (this.form.valid) {
      const formData = this.form.value;
      this.reproduccionService.postReproduccion(formData)
          .subscribe(
              (res) => {
                console.log('Respuesta del servidor:', res);
                this.closeModal();
                this.form.reset();
                this.getReproduccion();
              },
              (error) => {
                console.error('Error al guardar medicina:', error);
              }
          );

    } else {
      console.log('Formulario no válido');
    }
  }
  getCurrentDate() {
    return new Date().toISOString().split('T')[0];
  }

  getReproduccion() {
    this.reproduccionService.getReproduccion()
      .pipe(
        catchError((error) => {
          console.error('Error al obtener las reproduccion:', error);
          throw error;
        })
      )
      .subscribe((res) => {
        this.reproduccionService.reproducciones = res as Reproduccion[];
        console.log(res);
      });
  }
  getGanados() {
    this.ganadoService.getGanados()
      .pipe(
        catchError((error) => {
          console.error('Error al obtener los ganados:', error);
          throw error;
        })
      )
      .subscribe((res) => {
        this.ganadoService.ganados = res as Ganado[];
        console.log('Ganados obtenidos:', this.ganadoService.ganados);
      });
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

  @ViewChild('exampleModalEdit') exampleModalEdit!: ElementRef;
  closeModalEdit() {
    if (this.exampleModalEdit) {
      const modalElement = this.exampleModalEdit.nativeElement;
      modalElement.classList.remove('show');
      modalElement.style.display = 'none';
    }
  }

  deleteReproduccion(reproduccion: Reproduccion | undefined) {
    console.log('Reproducción antes de eliminar:', reproduccion);
    if (reproduccion && reproduccion.reproduccion_id) {
      this.reproduccionService.deleteReproduccion(reproduccion.reproduccion_id)
          .pipe(
              catchError((error) => {
                console.error('Error al eliminar la  reproduccion:', error);
                throw error;
              })
          )
          .subscribe(() => {
            this.getReproduccion();
          });
    } else {
      console.error('Error: reproduccion o reproduccion.reproduccion_id es undefined');
    }
  }


}
