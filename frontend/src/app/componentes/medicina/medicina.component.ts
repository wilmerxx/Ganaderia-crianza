import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MedicinaService} from "../../service/medicina.service";
import {Medicina} from "../../models/medicina.model";
import {GanadoService} from "../../service/ganado.service";
import {FormControl, FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import {Ganado} from "../../models/ganado";
import {Observable} from "rxjs";

@Component({
  selector: 'app-medicina',
  templateUrl: './medicina.component.html',
  styleUrls: ['./medicina.component.css']
})
export class MedicinaComponent implements OnInit {

  medicina_id: string = '';
  medicina: Medicina = new Medicina();
  form!: FormGroup;
  resultados$!: Observable<string[]>;
  medicinas: Medicina[] = [];
  textoBusqueda: string = '';
  constructor(
    protected medicinaService: MedicinaService,
    protected ganadoService: GanadoService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.formularioNuevaMedicina();
    this.getMedicinas();
    this.getGanados();
  }

  buscarGanado() {
    if (this.textoBusqueda !== '') {
      this.ganadoService.busquedaGanado(this.textoBusqueda).subscribe((res) => {
        this.ganadoService.ganados = res as Ganado[];
      });
    }
  }

  private formularioNuevaMedicina() {
    this.form = this.formBuilder.group({
        ganado: new FormControl('', [Validators.required]),
        sintomas: new FormControl('', [Validators.required]),
        diagnostico: new FormControl(''),
        tratamiento: new FormControl(''),
        fecha_vacuna: new FormControl('', [Validators.required]),
    });
  }

    guardar(event: Event) {
        event.preventDefault();

        if (this.form.valid) {
            console.log(this.form.value);
            const formData = this.form.value;

            const selectedGanadoId = formData.ganado.ganado_id;
            const selectedGanado = this.ganadoService.ganados.find(g => g.ganado_id === String(selectedGanadoId));

            if (selectedGanado) {
                formData.ganado_id = selectedGanado.ganado_id;
                formData.ganado_nombre = selectedGanado.nombre_ganado;

                this.medicinaService.postMedicina(formData).subscribe(
                    (res) => {
                        console.log('Respuesta del servidor:', res);
                        this.closeModal();
                        this.form.reset();
                        this.getMedicinas();
                    },
                    (error) => {
                        console.error('Error al guardar:', error);
                        // Muestra un mensaje de error al usuario según tus necesidades
                    }
                );
            } else {
                console.log('Ganado no encontrado o ganado_id no definido');
                // Puedes mostrar un mensaje de error al usuario según tus necesidades
            }
        } else {
            console.log('Formulario no válido');

            // Mostrar detalles sobre los errores de validación
            Object.keys(this.form.controls).forEach(key => {
                const control = this.form.get(key);
                const controlErrors = control?.errors;

                if (controlErrors != null) {
                    console.error(`Control: ${key}, Errores: `, controlErrors);
                }
            });
        }
    }

    getError(controlName: string) {
    const control = this.form.get(controlName);
    return control?.hasError('required') ? `El ${controlName} es requerido` : '';
  }


  getCurrentDate() {
    return new Date().toISOString().split('T')[0];
  }

  limpiarFormulario(form: NgForm){
    form.reset();
  }

  getMedicinas(){
    this.medicinaService.getMedicinas().subscribe((res) =>{
      this.medicinaService.medicinas = res as Medicina[];
      this.getGanados();
      console.log(res);
    })
  }

  getGanados() {
    this.ganadoService.getGanados().subscribe((res) =>{
      this.ganadoService.ganados = res as Ganado[];
      console.log(res);
    })
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

  openModalEdit(medicina: any) {
    if (this.exampleModalEdit) {
      const modalElement = this.exampleModalEdit.nativeElement;
      modalElement.classList.add('show');
      modalElement.style.display = 'block';

      this.medicinaService.getMedicinaID(medicina.medicinaId).subscribe(res => {
        this.medicina = res;
        console.log("Funcion OpenModalEdit");
        console.log(this.medicina);
      });
    }
  }
  deleteMedicina(medicina_id: string | undefined) {
    if (medicina_id) {
      this.medicinaService.deleteMedicina(medicina_id).subscribe((res) => { });
      this.getMedicinas();
    }
  }
}
