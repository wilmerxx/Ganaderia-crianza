import {Component, ElementRef, ViewChild, OnInit} from '@angular/core';
import {Producto} from "../../models/producto";
import {FormBuilder, FormControl, FormGroup, NgForm} from "@angular/forms";
import {Observable} from "rxjs";
import {ProductoMongoService} from "../../service/producto-mongo.service";
import {catchError} from "rxjs/operators";
import Swal from "sweetalert2";

@Component({
  selector: 'app-bodega',
  templateUrl: './bodega.component.html',
  styleUrls: ['./bodega.component.css']
})
export class BodegaComponent implements OnInit{
  producto: Producto = new Producto();
  form!: FormGroup;
  resultados$!: Observable<string[]>;
  textoBusqueda: string = '';

  constructor(
    protected productoMongoService: ProductoMongoService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.formularioNuevoProducto();
    this.getProductos();
  }

  private formularioNuevoProducto() {
    this.form = this.formBuilder.group({
      nombreproducto: new FormControl(''),
      unidad_medida: new FormControl(''),
      marca: new FormControl(''),
      tipo_producto: new FormControl(''),
      fecha_vencimiento: new FormControl(''),
    });
  }
  guardar(event: Event) {
    event.preventDefault();
    console.log(this.form.value);
    if (this.form.valid) {
      const formData = this.form.value;
      this.productoMongoService.postProducto(formData)
        .subscribe(
          (res) => {
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Producto guardado con éxito',
              showConfirmButton: false,
              timer: 1500
            });
            console.log('Respuesta del servidor:', res);
            this.closeModal();
            this.form.reset();
            this.getProductos();
          },
          (error) => {
            console.error('Error al guardar producto:', error);
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

  getProductos() {
    this.productoMongoService.getProductos()
      .pipe(
        catchError((error) => {
          console.error('Error al obtener las productos:', error);
          throw error;
        })
      )
      .subscribe((res) => {
        this.productoMongoService.productos = res as Producto[];
        console.log(res);
      });
  }


  deleteProductos(id: number | undefined) {
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
        this.productoMongoService.deleteProducto(id).subscribe(() => {
          Swal.fire(
            '¡Eliminado!',
            'La alimentación ha sido eliminada.',
            'success'
          );
          this.productoMongoService.productos = this.productoMongoService.productos.filter(item => item.id !== id);
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

  openModalEdit(producto: any) {
    if (this.exampleModalEdit) {
      const modalElement = this.exampleModalEdit.nativeElement;
      modalElement.classList.add('show');
      modalElement.style.display = 'block';
      this.productoMongoService.getProductoID(producto.id_producto).subscribe(res =>{
        this.producto = res;
        console.log("Funcion OpenModalEdit");
        console.log(this.producto);
      });
    }
  }
}
