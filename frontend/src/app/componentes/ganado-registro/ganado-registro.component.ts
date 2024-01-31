import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {GanadoService} from '../../service/ganado.service';
import {Ganado} from "../../models/ganado";
import {FormGroup, FormBuilder, FormControl, Validator, Validators, NgForm} from '@angular/forms';
import {MAT_DATEPICKER_VALIDATORS} from "@angular/material/datepicker";
import {Observable} from "rxjs";
import {Medicina} from "../../models/medicina.model";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'ganado-registro',
  templateUrl: './ganado-registro.component.html',
  styleUrls: ['./ganado-registro.component.css']
})

export class GanadoRegistroComponent implements OnInit {

  ganado_id: string = '';
  ganado: Ganado = new Ganado();
  form!: FormGroup;
  resultados$!: Observable<string[]>;
  ganados: Ganado[] = [];
  textoBusquedo: string = '';

  constructor(protected ganadoService: GanadoService,
              private formBuilder: FormBuilder) {
    //validaciones
  }

  ngOnInit(): void {
    this.ganado;
    this.ganadoService.getGanadoTipo('Vaca');
    this.ganadoService.getGanadoTipo('Toro');
    this.formularioNuevoGanado();
    this.getGanados();
    console.log(this.form.value);

    this.resultados$ = this.ganadoService.busquedaGanado(this.textoBusquedo);
  }


  private formularioNuevoGanado() {
    this.form = this.formBuilder.group({
      codigo: new FormControl('', [Validators.required]),
      nombre_ganado: new FormControl('', [Validators.required]),
      raza: new FormControl('', [Validators.required]),
      peso: new FormControl('', [Validators.required]),
      sexo: new FormControl('', [Validators.required]),
      fechaNacimiento: new FormControl('', [Validators.required]),
      ganado_madre_id: new FormControl(''),
      ganado_padre_id: new FormControl(''),
    });
  }

  guardar(event: Event) {
    event.preventDefault();
    console.log(this.form.value);
    if (this.form.valid) {
      const formData = this.form.value;
      this.ganadoService.postGanado(formData)
          .subscribe(
              (res) => {
                console.log('Respuesta del servidor:', res);
                this.closeModal();
                this.form.reset();
                this.getGanados();
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


  //obtener ganado por id
  buscarGanadoID(id: string) {
    return this.ganadoService.getGanadoID(id).subscribe((res) => {
      this.ganadoService.selectedGanado = res as Ganado;
    });
  }


  limpiarFormulario() {
    // Reiniciar el formulario reactivo
    this.form.reset();
  }


  getGanados() {
    this.ganadoService.getGanados().subscribe((res) => {
      for (let i = 0; i < res.length; i++) {
        this.ganadoService.ganados[i] = res[i];
        //calcular edad del ganado en meses
        this.ganadoService.ganados[i].edad = this.ganadoService.calcularEdad(res[i].fechaNacimiento ?? '');
        this.ganadoService.getGanadoID(res[i].ganado_madre_id ?? '').subscribe((res2) => {
          this.ganadoService.ganados[i].nombre_madre = res2.nombre_ganado;
        });
        this.ganadoService.getGanadoID(res[i].ganado_padre_id ?? '').subscribe((res3) => {
          this.ganadoService.ganados[i].nombre_padre = res3.nombre_ganado;
        });
      }

    })
  }

  // Método para determinar si la fila actual está en modo de edición
  putGanado(form: NgForm) {
    console.log(form.value);
    this.ganadoService.putGanado(form.value).subscribe((res) => {
      console.log(res);
      this.closeModalEdit();
    });
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
      this.ganadoService.getGanadoID(ganado.ganado_id).subscribe(res => {
        this.ganado = res;
        console.log("Funcion OpenModalEdit");
        console.log(this.ganado);
      });
    }
  }

  deleteGanado(ganado_id: number | undefined) {
    if (ganado_id != 0) {
      this.ganadoService.deleteGanado(ganado_id)
          .pipe(
              catchError((error) => {
                console.error('Error al eliminar la area:', error);
                throw error;
              })
          )
          .subscribe(() => {
            this.getGanados();
          });
    } else {
      console.error('No se puede eliminar el area');
    }
  }
}

