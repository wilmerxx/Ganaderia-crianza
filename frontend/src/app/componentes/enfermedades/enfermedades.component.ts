import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {GanadoService} from "../../service/ganado.service";
import {Ganado} from "../../models/ganado";
import {EnfermedadesService} from "../../service/enfermedades.service";
import {FormBuilder, FormControl, FormGroup, NgForm, Validators} from "@angular/forms";
import {Enfermedad} from "../../models/enfermedades.model";
import Swal from "sweetalert2";
import {catchError} from "rxjs/operators";
import {Medicina} from "../../models/medicina.model";

@Component({
  selector: 'app-enfermedades',
  templateUrl: './enfermedades.component.html',
  styleUrls: ['./enfermedades.component.css']
})
export class EnfermedadesComponent implements OnInit {

  controlEnfermedades: Enfermedad = new Enfermedad();
  form!: FormGroup;
  textoBusqueda: string = '';

  constructor(
    protected enfermedadesService: EnfermedadesService,
    protected ganadoService: GanadoService,
    private formBuilder: FormBuilder
  ){}
  ngOnInit(): void {
    this.formularioNuevaEnfermedad();
    this.getEnfermedad();
    this.getGanados();
  }
  private formularioNuevaEnfermedad() {
    this.form = this.formBuilder.group({
      tipo_control: new FormControl('', [Validators.required]),
      pesoActual: new FormControl(''),
      fechaControl: new FormControl(''),
      observaciones: new FormControl('', [Validators.required]),
      ganadoId: new FormControl('', [Validators.required]),

    });
  }

  guardar(event: Event) {
    event.preventDefault();
    console.log(this.form.value);
    if (this.form.valid) {
      const formData = this.form.value;
      this.enfermedadesService.postEnfermedad(formData)
        .subscribe(
          (res) => {
            console.log('Respuesta del servidor:', res);
            this.closeModal();
            this.form.reset();
            this.getEnfermedad();
          },
          (error) => {
            console.error('Error al guardar enfermedad:', error);
          }
        );

    } else {
      console.log('Formulario no válido');
    }
  }
  getEnfermedad() {
    this.enfermedadesService.getEnfermedad()
      .pipe(
        catchError((error) => {
          console.error('Error al obtener las enfermedad:', error);
          throw error;
        })
      )
      .subscribe((res) => {
        this.enfermedadesService.enfermedades = res as Enfermedad[];
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

  deleteEnfermedades(enfermedades: Enfermedad | undefined) {
    if (enfermedades && enfermedades.control_id) {
      this.enfermedadesService.deleteEnfermedades(enfermedades.control_id)
          .pipe(
              catchError((error) => {
                console.error('Error al eliminar la enfermedad:', error);
                throw error;
              })
          )
          .subscribe(() => {
            this.getEnfermedad();
          });
    } else {
      console.error('Error: medicina o medicina.medicinaId es undefined');
    }
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
