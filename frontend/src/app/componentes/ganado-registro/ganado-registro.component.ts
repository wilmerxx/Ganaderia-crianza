import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {GanadoService} from '../../service/ganado.service';
import {Ganado} from "../../models/ganado";
import {FormGroup, FormBuilder, FormControl, Validator, Validators, NgForm} from '@angular/forms';
import {MAT_DATEPICKER_VALIDATORS} from "@angular/material/datepicker";
import {Observable} from "rxjs";

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

  constructor(protected ganadoService: GanadoService, private formBuilder: FormBuilder) {
    //validaciones
    this.buscarGanado(this.textoBusquedo);
  }

  ngOnInit(): void {
    this.ganado;
    this.ganadoService.getGanadoTipo('Vaca');
    this.ganadoService.getGanadoTipo('Toro');
    this.formularioNuevoGanado();
    console.log(this.form.value);

    this.resultados$ = this.ganadoService.busquedaGanado(this.textoBusquedo);
    this.buscarGanado('');
  }

  buscarGanado(query: string){
    if(query != ''){
     this.ganadoService.busquedaGanado(query).subscribe((res) =>{
        this.ganadoService.ganados = res as Ganado[];
        for(let i = 0; i < res.length; i++){
          this.ganadoService.ganados[i] = res[i];
          //calcular edad del ganado en meses
          this.ganadoService.ganados[i].edad = this.ganadoService.calcularEdad(res[i].fechaNacimiento??'');
          this.ganadoService.getGanadoID(res[i].madre_id??'').subscribe((res2) =>{
            this.ganadoService.ganados[i].nombreMadre = res2.nombre_ganado;
          });
          this.ganadoService.getGanadoID(res[i].padre_id??'').subscribe((res3) =>{
            this.ganadoService.ganados[i].nombrePadre = res3.nombre_ganado;
          });
        }
      });
    }else if(query == ''){
      this.getGanados();
    }
  }


  private formularioNuevoGanado() {
    this.form = this.formBuilder.group({
      codigo: new FormControl('', [Validators.required]),
      nombre_ganado: new FormControl('', [Validators.required]),
      raza: new FormControl('', [Validators.required]),
      peso: new FormControl('', [Validators.required]),
      sexo: new FormControl('', [Validators.required]),
      fechaNacimiento: new FormControl('', [Validators.required]),
      tipo: new FormControl(''),
      madre_id: new FormControl(''),
      padre_id: new FormControl(''),
      estado: new FormControl('')
    });
  }

guardar(even: Event){
  even.preventDefault();

    const value = this.form.value;
    console.log(value);
    this.ganadoService.postGanado(this.form.value).subscribe((res) => {
      console.log(res);
      this.buscarGanado(this.textoBusquedo);
      this.closeModal();
      this.limpiarFormulario(this.form.value);
    });
}

  getCurrentDate() {
    return new Date().toISOString().split('T')[0];
  }



  //obtener ganado por id
  buscarGanadoID(id: string){
    return this.ganadoService.getGanadoID(id).subscribe((res) =>{
      this.ganadoService.selectedGanado = res as Ganado;
    });
  }


  limpiarFormulario(form: NgForm){
    form.reset();
  }



  getGanados() {
  this.ganadoService.getGanados().subscribe((res) =>{
      for(let i = 0; i < res.length; i++){
        this.ganadoService.ganados[i] = res[i];
        //calcular edad del ganado en meses
        this.ganadoService.ganados[i].edad = this.ganadoService.calcularEdad(res[i].fechaNacimiento??'');
        this.ganadoService.getGanadoID(res[i].madre_id??'').subscribe((res2) =>{
          this.ganadoService.ganados[i].nombreMadre = res2.nombre_ganado;
        });
        this.ganadoService.getGanadoID(res[i].padre_id??'').subscribe((res3) =>{
          this.ganadoService.ganados[i].nombrePadre = res3.nombre_ganado;
        });
      }

    })
  }

  // Método para determinar si la fila actual está en modo de edición
  putGanado(form: NgForm){
    console.log(form.value);
    this.ganadoService.putGanado(form.value).subscribe((res) => {
      console.log(res);
      this.buscarGanado(this.textoBusquedo);
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
      this.ganadoService.getGanadoID(ganado.ganado_id).subscribe(res =>{
        this.ganado = res;
        console.log("Funcion OpenModalEdit");
        console.log(this.ganado);
      });
    }
  }

  deleteGanado(ganado_id: string | undefined) {
    if (ganado_id) {
      this.ganadoService.deleteGanado(ganado_id).subscribe((res) => { });
      this.getGanados();
      window.location.reload();
    }
  }

}



