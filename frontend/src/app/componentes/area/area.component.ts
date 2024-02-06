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
            }
        );

    } else {
      console.log('Formulario no válido');
    }
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


}
