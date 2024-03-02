import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Producto} from "../../models/producto";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Inventario} from "../../models/inventario";
import {InventarioMongoService} from "../../service/inventario-mongo.service";
import {ProductoMongoService} from "../../service/producto-mongo.service";
import Swal from "sweetalert2";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-consumo',
  templateUrl: './consumo.component.html',
  styleUrls: ['./consumo.component.css']
})
export class ConsumoComponent implements OnInit{
  inventario: Inventario = new Inventario();
  form!: FormGroup;

  constructor(
      protected inventarioMongoService: InventarioMongoService,
      protected productoMongoService: ProductoMongoService,
      private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
  this.formularioNuevoInventario();
    this.getInventarios();
    this.getProductos();
  }
  private formularioNuevoInventario() {
    this.form = this.formBuilder.group({
      id_producto: new FormControl(''),
      cantidad_producto: new FormControl(''),
      fecha_inventario: new FormControl(''),
    });
  }
  guardar(event: Event) {
    event.preventDefault();
    console.log(this.form.value);
    if (this.form.valid) {
      const formData = this.form.value;
      this.inventarioMongoService.postInventario(formData)
          .subscribe(
              (res) => {
                Swal.fire({
                  position: 'center',
                  icon: 'success',
                  title: 'Inventario guardado con éxito',
                  showConfirmButton: false,
                  timer: 1500
                });
                console.log('Respuesta del servidor:', res);
                this.closeModal();
                this.form.reset();
                this.getInventarios();
                console.log('Inventario guardado:', formData);
              },
              (error) => {
                console.error('Error al guardar inventario:', error);
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

  getInventarios() {
    this.inventarioMongoService.getInventario().subscribe((res) => {
          this.inventarioMongoService.inventario = res as Inventario[];
          console.log(res);
        },
      (error) => {
        console.error('Error al obtener los inventarios:', error);
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

  deleteInventarios(id: number | undefined) {
    if (id) {Swal.fire({
      title: '¿Estás seguro?',
      text: "¡No podrás revertir esto!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Sí, eliminarlo'
    }).then((result) => {
      if (result.isConfirmed) {
        this.inventarioMongoService.deleteInventario(id).subscribe(() => {
          Swal.fire(
              '¡Eliminado!',
              'La alimentación ha sido eliminada.',
              'success'
          );
          this.inventarioMongoService.inventario = this.inventarioMongoService.inventario.filter(item => item.id_producto !== id);
        });
      }
    });
    } else {
      console.error('No se puede eliminar el producto');
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

  openModalEdit(inventario: any) {
    if (this.exampleModalEdit) {
      const modalElement = this.exampleModalEdit.nativeElement;
      modalElement.classList.add('show');
      modalElement.style.display = 'block';
      this.inventarioMongoService.getInventarioID(inventario).subscribe(res =>{
        this.inventario = res;
        console.log("Funcion OpenModalEdit");
        console.log(this.inventario);
      });
    }
  }
}
