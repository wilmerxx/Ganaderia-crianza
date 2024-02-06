import {ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {GanadoService} from '../../service/ganado.service';
import {Ganado} from "../../models/ganado";
import {FormGroup, FormBuilder, FormControl, Validator, Validators, NgForm} from '@angular/forms';
import {MAT_DATEPICKER_VALIDATORS} from "@angular/material/datepicker";
import {Observable} from "rxjs";
import {Medicina} from "../../models/medicina.model";
import {catchError} from "rxjs/operators";
import Swal from "sweetalert2";

@Component({
  selector: 'ganado-registro',
  templateUrl: './ganado-registro.component.html',
  styleUrls: ['./ganado-registro.component.css']
})

export class GanadoRegistroComponent implements OnInit {

  ganado_id: string = '';
  ganado: Ganado = new Ganado();
  form!: FormGroup;
  ganados: Ganado[] = [];
  textoBuscado: string = '';

  currentPage: number = 1;
  totalPages: number = 0;
  pagesArray: number[] = [];

  constructor(protected ganadoService: GanadoService,
              private formBuilder: FormBuilder
  ) {
    //validaciones
  }

  ngOnInit(): void {
    this.ganado;
    this.ganadoService.getGanadoTipo('Vaca');
    this.ganadoService.getGanadoTipo('Toro');
    this.formularioNuevoGanado();
    this.getGanados();
  }

  getGanadosPaginacion2(texto: string, page: number, size: number) {
    this.currentPage = page;
    this.ganadoService.getGanadosPaginacion(texto, page, size).subscribe((res) => {
      this.ganados = res.content;
      this.totalPages = res.totalPages;
      this.pagesArray = Array.from({length: this.totalPages}, (v, k) => k + 1);
    });
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
                Swal.fire({
                  position: 'center',
                  icon: 'success',
                  title: 'Ganado guardado con éxito',
                  showConfirmButton: false,
                  timer: 1500
                });
                console.log('Respuesta del servidor:', res);
                this.closeModal();
                this.form.reset();
                this.getGanados();
              },
              (error) => {
                console.error('Error al guardar  ganado:', error);
                Swal.fire({
                  icon: 'error',
                  title: 'Error al guardar',
                  text:  error.error.message,
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


  //obtener ganado por id
  buscarGanadoID(id: string) {
    return this.ganadoService.getGanadoID(id).subscribe((res) => {
      this.ganadoService.selectedGanado = res as Ganado;
    });
  }

  getGanados(envent?: Event) {
    if(envent){
      envent.preventDefault();
    }
    this.ganadoService.getGanados().subscribe((res) => {
      this.ganadoService.ganados = res;
      // console.log(this.ganadoService.ganados);
    })
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

  // Método para determinar si la fila actual está en modo de edición
  putGanado(form: NgForm,event: Event) {
    event.preventDefault();
    console.log(form.value);
    this.ganadoService.putGanado(form.value).subscribe((res) => {
      console.log(res);
      this.closeModalEdit();
      this.getGanados();

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
      Swal.fire({
        title: '¿Estás seguro?',
        text: "¡No podrás revertir esto!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sí, eliminarlo'
      }).then((result) => {
        if (result.isConfirmed) {
          this.ganadoService.deleteGanado(ganado_id).subscribe((res) => {
            console.log(res);
            this.getGanados();
          });
        }
      });
    } else {
      console.error('No se puede eliminar el ganado');
    }
  }
}
