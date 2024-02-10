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
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Control de enfermedad guardado con éxito',
              showConfirmButton: false,
              timer: 1500
            });
            console.log('Respuesta del servidor:', res);
            this.closeModal();
            this.form.reset();
            this.getEnfermedad();
          },
          (error) => {
            console.error('Error al guardar el control de enfermedad:', error);
            Swal.fire({
              icon: 'error',
              title: error.error.status,
              text: `${error.error.message}`
            });
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
  putEnfermedades(form: NgForm,event: Event) {
    event.preventDefault();
    console.log(form.value);
    this.enfermedadesService.putEnfermedades(form.value).subscribe((res) => {
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Enfermedad actualizado con éxito',
            showConfirmButton: false,
            timer: 1500
          })
          console.log(res);
          this.closeModalEdit();
          this.getEnfermedad();
        },
        (error) => {
          console.error('Error al guardar enfermedad:', error);
          Swal.fire({
            icon: 'error',
            title: error.error.status,
            text: `${error.error.message}`
          });
        }
    );
    console.log(form.value);
  }
  deleteEnfermedades(control_id: number | undefined) {
    if (control_id) {Swal.fire({
      title: '¿Estás seguro?',
      text: "¡No podrás revertir esto!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Sí, eliminarlo'
    }).then((result) => {
      if (result.isConfirmed) {
        this.enfermedadesService.deleteEnfermedades(control_id).subscribe(() => {
          Swal.fire(
            '¡Eliminado!',
            'La alimentación ha sido eliminada.',
            'success'
          );
          // Manually remove the deleted item from the local array
          this.enfermedadesService.enfermedades = this.enfermedadesService.enfermedades.filter(item => item.control_id !== control_id);
        });
      }
    });
    } else {
      console.error('No se puede eliminar el alimento');
    }
  }

  getCurrentDate() {
    return new Date().toISOString().split('T')[0];
  }
  @ViewChild('exampleModal') exampleModal!: ElementRef;

  closeModal() {
    if (this.exampleModal) {
      const modalElement = this.exampleModal.nativeElement;
      modalElement.classList.remove('show');
      modalElement.style.display = 'none';
    }
  }
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
  openModalEdit(enfermedad: any) {
    if (this.exampleModalEdit) {
      const modalElement = this.exampleModalEdit.nativeElement;
      modalElement.classList.add('show');
      modalElement.style.display = 'block';
      this.enfermedadesService.getEnfermedadID(enfermedad.control_id).subscribe(res => {
        this.controlEnfermedades = res;
        console.log("Funcion OpenModalEdit");
        console.log(this.controlEnfermedades);
      });
    }
  }

}
