import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ReproduccionService} from "../../service/reproduccion.service";
import {Reproduccion} from "../../models/reproduccion.model";
import {FormControl, FormBuilder, FormGroup, NgForm, Validators } from "@angular/forms";
import Swal from 'sweetalert2';
import {Ganado} from "../../models/ganado";
import {GanadoService} from "../../service/ganado.service";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-reproduccion',
  templateUrl: './reproduccion.component.html',
  styleUrls: ['./reproduccion.component.css']
})
export class ReproduccionComponent implements OnInit {

  reproduccion: Reproduccion = new Reproduccion();
  form!: FormGroup;
  textoBuscado: string = '';

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
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Reproducción guardado con éxito',
              showConfirmButton: false,
              timer: 1500
            }).then(r => {
                console.log(res);
                this.getReproduccion();
            });
            this.closeModal();
            this.form.reset();
            this.getReproduccion();
          },
          (error) => {
            console.error('Error al guardar la reproduccion:', error);
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

  putReproduccion(form: NgForm,event: Event) {
    event.preventDefault();
    console.log(form.value);
    this.reproduccionService.putReproduccion(form.value).subscribe((res) => {
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Reproducción actualizado con éxito',
            showConfirmButton: false,
            timer: 1500
          })
          console.log(res);
          this.closeModalEdit();
          this.getReproduccion();
        },
        (error) => {
          console.error('Error al guardar reproduccion:', error);
          Swal.fire({
            icon: 'error',
            title: error.error.status,
            text: `${error.error.message}`
          });
        }
    );
    console.log(form.value);
  }

  getGanadosPaginacion(texto:string, page:number, size:number){
    console.log("Texto: " + texto);
    console.log("Page: " + page);
    console.log("Size: " + size);
    if(texto == ''){
      this.getGanados();
    }else{
      this.ganadoService.getGanadosPaginacion(texto, page, size).subscribe((res) => {
        this.ganadoService.ganados = res;
        console.log(this.ganadoService.ganados);
      });
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

  @ViewChild('exampleModalEdit') exampleModalEdit!: ElementRef;
  closeModalEdit() {
    if (this.exampleModalEdit) {
      const modalElement = this.exampleModalEdit.nativeElement;
      modalElement.classList.remove('show');
      modalElement.style.display = 'none';
    }
  }
  openModalEdit(reproduccion: any) {
    if (this.exampleModalEdit) {
      const modalElement = this.exampleModalEdit.nativeElement;
      modalElement.classList.add('show');
      modalElement.style.display = 'block';
      this.reproduccionService.getReproduccionesID(reproduccion.reproduccion_id).subscribe(res => {
        this.reproduccion = res;
        console.log("Funcion OpenModalEdit");
        console.log(this.reproduccion);
      });
    }
  }
  deleteReproduccion(reproduccion_id: number | undefined) {
    if (reproduccion_id) {Swal.fire({
      title: '¿Estás seguro?',
      text: "¡No podrás revertir esto!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Sí, eliminarlo'
    }).then((result) => {
      if (result.isConfirmed) {
        this.reproduccionService.deleteReproduccion(reproduccion_id).subscribe(() => {
          Swal.fire(
            '¡Eliminado!',
            'La alimentación ha sido eliminada.',
            'success'
          );
          // Manually remove the deleted item from the local array
          this.reproduccionService.reproducciones = this.reproduccionService.reproducciones.filter(item => item.reproduccion_id !== reproduccion_id);
        });
      }
    });
    } else {
      console.error('No se puede eliminar el alimento');
    }
  }

}
