import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AlimentacionService} from "../../service/alimentacion.service";
import {Alimentacion} from "../../models/alimentacion.model";
import {FormBuilder, FormControl, FormGroup, NgForm} from "@angular/forms";
import {GanadoService} from "../../service/ganado.service";
import {catchError} from "rxjs/operators";
import {Ganado} from "../../models/ganado";
import Swal from "sweetalert2";
import {ProductoMongoService} from "../../service/producto-mongo.service";
import {Producto} from "../../models/producto";

@Component({
  selector: 'app-alimentacion',
  templateUrl: './alimentacion.component.html',
  styleUrls: ['./alimentacion.component.css']
})
export class AlimentacionComponent implements OnInit {

 alimentacion: Alimentacion = new Alimentacion();
 form!: FormGroup;
  constructor(protected alimentacionService: AlimentacionService,
              protected ganadoService: GanadoService,
              protected productoMongoService: ProductoMongoService,
              private formBuilder: FormBuilder
              ) {
  }
  ngOnInit(): void {
    this.formularioNuevaAlimentacion();
    this.getAlimentacion();
    this.getGanados();
    this.getProductos();
  }

  private formularioNuevaAlimentacion() {
    this.form = this.formBuilder.group({
      nombre_suplemento: new FormControl(''),
      cantidad_suplemento: new FormControl(''),
      fecha_alimentacion: new FormControl(''),
      ganado_id: new FormControl(''),
    });
  }

  guardar(event: Event) {
    event.preventDefault();
    console.log(this.form.value);
    if (this.form.valid) {
      const formData = this.form.value;
      this.alimentacionService.postAlimentacion(formData)
          .subscribe(
              (res) => {
                Swal.fire({
                  position: 'center',
                  icon: 'success',
                  title: 'Alimentación guardada con éxito',
                  showConfirmButton: false,
                  timer: 1500
                });
                console.log('Respuesta del servidor:', res);
                this.closeModal();
                this.form.reset();
                this.getAlimentacion();
              },
              (error) => {
                console.error('Error al guardar medicina:', error);
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

  getAlimentacion() {
    this.alimentacionService.getAlimentacion()
        .pipe(
            catchError((error) => {
              console.error('Error al obtener las medicinas:', error);
              throw error;
            })
        )
        .subscribe((res) => {
          this.alimentacionService.alimentacion = res as Alimentacion[];
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
  getProductos() {
    this.productoMongoService.getProductos()
      .pipe(
        catchError((error) => {
          console.error('Error al obtener los productos:', error);
          throw error;
        })
      )
      .subscribe((res) => {
        this.productoMongoService.productos = res as Producto[];
        console.log('Productos obtenidos:', this.productoMongoService.productos);
      });
  }

  // Método para actualizar todos los datos de la medicina
  putAlimentacion(form: NgForm,event: Event) {
      event.preventDefault();
      console.log(form.value);
      this.alimentacionService.putAlimentacion(form.value).subscribe((res) => {
              Swal.fire({
                  position: 'center',
                  icon: 'success',
                  title: 'Alimentacion actualizado con éxito',
                  showConfirmButton: false,
                  timer: 1500
              })
              console.log(res);
              this.closeModalEdit();
              this.getAlimentacion();
          },
          (error) => {
              console.error('Error al guardar alimentacion:', error);
              Swal.fire({
                  icon: 'error',
                  title: error.error.status,
                  text: `${error.error.message}`
              });
          }
      );
      console.log(form.value);
  }

  deleteAlimentacion(alimentacion_id: number | undefined) {
    if (alimentacion_id) {Swal.fire({
      title: '¿Estás seguro?',
      text: "¡No podrás revertir esto!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Sí, eliminarlo'
    }).then((result) => {
        if (result.isConfirmed) {
            this.alimentacionService.deleteAlimentacion(alimentacion_id).subscribe(() => {
                Swal.fire(
                    '¡Eliminado!',
                    'La alimentación ha sido eliminada.',
                    'success'
                );
                // Manually remove the deleted item from the local array
                this.alimentacionService.alimentacion = this.alimentacionService.alimentacion.filter(item => item.alimentacion_id !== alimentacion_id);
            });
        }
    });
    } else {
      console.error('No se puede eliminar el alimento');
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
  closeModal() {
    if (this.exampleModal) {
      const modalElement = this.exampleModal.nativeElement;
      modalElement.classList.remove('show');
      modalElement.style.display = 'none';
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

  openModalEdit(alimentacion: any) {
    if (this.exampleModalEdit) {
      const modalElement = this.exampleModalEdit.nativeElement;
      modalElement.classList.add('show');
      modalElement.style.display = 'block';
      this.getGanados();
      this.alimentacionService.getAlimentacionID(alimentacion.alimentacion_id).subscribe(res =>{
        this.alimentacion = res;
        console.log("Funcion OpenModalEdit");
        console.log(this.alimentacion);
      });
    }
  }
}
