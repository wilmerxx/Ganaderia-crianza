import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AreaService} from "../../service/area.service";
import {Area} from "../../models/area.model";
import { HttpClient } from '@angular/common/http';
import {FormBuilder, FormControl, FormGroup, NgForm, Validators} from "@angular/forms";
import Swal from 'sweetalert2';
import {GanadoService} from "../../service/ganado.service";
import {catchError} from "rxjs/operators";
import {Medicina} from "../../models/medicina.model";
import {Observable} from "rxjs";
import {Ganado} from "../../models/ganado";
import {error} from "@angular/compiler-cli/src/transformers/util";


@Component({
  selector: 'app-area',
  templateUrl: './area.component.html',
  styleUrls: ['./area.component.css']
})
export class AreaComponent implements OnInit {

  area: Area = new Area();
  resultados$!: Observable<string[]>;
  form!: FormGroup;

  constructor(protected areaService: AreaService,
              protected ganadoService: GanadoService,
              private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.formularioNuevaArea();
    this.getAreas();
    this.getGanados();
  }

  private formularioNuevaArea() {
    this.form = this.formBuilder.group({
      nombreArea: new FormControl('', [Validators.required]),
      tipoArea: new FormControl(''),
      tipoPasto: new FormControl(''),
      superficie: new FormControl('', [Validators.required]),
      ganadoId: new FormControl('', [Validators.required]),
    });
  }

  putArea(form: NgForm,event: Event ){
      event.preventDefault();
      console.log(form.value)
      this.areaService.putArea(form.value).subscribe((res) => {
          console.log(res);
          this.closeModalEdit();
          this.getAreas();
      },
          (error)=>{
                console.error('Error al actualizar area:', error);
                Swal.fire({
                    icon: 'error',
                    title: error.error.status,
                    text: `${error.error.message}`
                });
          }
          );
      console.log(form.value);
    }

  guardar(event: Event) {
    event.preventDefault();
    console.log(this.form.value);
    if (this.form.valid) {
      const formData = this.form.value;
      this.areaService.postArea(formData)
        .subscribe(
          (res) => {
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Área guardado con éxito',
              showConfirmButton: false,
              timer: 1500
            });
            console.log('Respuesta del servidor:', res);
            this.closeModal();
            this.form.reset();
            this.getAreas();
          },
            (error) => {
              console.error('Error al guardar area:', error);
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
  getAreas() {
    this.areaService.getAreas()
      .pipe(
        catchError((error) => {
          console.error('Error al obtener las areas:', error);
          throw error;
        })
      )
      .subscribe((res) => {
        this.areaService.areas = res as Area[];
        console.log(res);
      });
  }
  deleteArea(areaId:number | undefined) {
    if (areaId!=0) {
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
          this.areaService.deleteArea(areaId).subscribe((res) => {
            console.log(res);
            this.getAreas();
          });
        }
      });
    } else {
      console.error('No se puede eliminar el area');
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

    openModalEdit(area: any) {
        if (this.exampleModalEdit) {
            const modalElement = this.exampleModalEdit.nativeElement;
            modalElement.classList.add('show');
            modalElement.style.display = 'block';
            this.getGanados();
            this.areaService.getAreaID(area.areaId).subscribe(res => {
                this.area = res;
                console.log("Funcion OpenModalEdit");
                console.log(this.area);
            });
        }
    }

}
