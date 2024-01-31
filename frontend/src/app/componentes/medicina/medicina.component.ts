import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MedicinaService } from "../../service/medicina.service";
import { Medicina } from "../../models/medicina.model";
import { GanadoService } from "../../service/ganado.service";
import { FormControl, FormBuilder, FormGroup, NgForm, Validators } from "@angular/forms";
import { Ganado } from "../../models/ganado";
import { Observable } from "rxjs";
import { catchError } from 'rxjs/operators';

@Component({
    selector: 'app-medicina',
    templateUrl: './medicina.component.html',
    styleUrls: ['./medicina.component.css']
})
export class MedicinaComponent implements OnInit {

    medicina: Medicina = new Medicina();
    form!: FormGroup;
    resultados$!: Observable<string[]>;
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


    private formularioNuevaMedicina() {
        this.form = this.formBuilder.group({
            sintomas: new FormControl('', [Validators.required]),
            diagnostico: new FormControl(''),
            tratamiento: new FormControl(''),
            fecha_vacuna: new FormControl('', [Validators.required]),
            ganado_id: new FormControl('', [Validators.required]),

        });
    }
    guardar(event: Event) {
        event.preventDefault();
      console.log(this.form.value);
      if (this.form.valid) {
            const formData = this.form.value;
                this.medicinaService.postMedicina(formData)
                    .subscribe(
                        (res) => {
                            console.log('Respuesta del servidor:', res);
                            this.closeModal();
                            this.form.reset();
                            this.getMedicinas();
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

    getMedicinas() {
        this.medicinaService.getMedicinas()
            .pipe(
                catchError((error) => {
                    console.error('Error al obtener las medicinas:', error);
                    throw error;
                })
            )
            .subscribe((res) => {
                this.medicinaService.medicinas = res as Medicina[];
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

    // Método para actualizar todos los datos de la medicina
    putMedicina(form: NgForm) {
        console.log(form.value);
        if (form.valid) {
            this.medicinaService.putMedicina(form.value).subscribe(
                response => {
                    // Manejar la respuesta según sea necesario
                    console.log('Medicina actualizada con éxito:', response);
                    this.closeModalEdit();
                    this.getMedicinas();
                },
                error => {
                    console.error('Error al actualizar la medicina:', error);
                    // Manejar el error
                }
            );
        } else {
            console.error('Error: medicinaId es undefined');
        }
    }

    deleteMedicina(medicina: Medicina | undefined) {
        if (medicina && medicina.medicina_id) {
            this.medicinaService.deleteMedicina(medicina.medicina_id)
                .pipe(
                    catchError((error) => {
                        console.error('Error al eliminar la medicina:', error);
                        throw error;
                    })
                )
                .subscribe(() => {
                    this.getMedicinas();
                });
        } else {
            console.error('Error: medicina o medicina.medicinaId es undefined');
        }
    }


    @ViewChild('exampleModal') exampleModal!: ElementRef;
    openModal() {
        if (this.exampleModal) {
            const modalElement = this.exampleModal.nativeElement;
            modalElement.classList.add('show');
            modalElement.style.display = 'block';
            this.form.reset(); // Restablecer el formulario
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

    openModalEdit(medicina: any) {
        if (this.exampleModalEdit) {
            const modalElement = this.exampleModalEdit.nativeElement;
            modalElement.classList.add('show');
            modalElement.style.display = 'block';
            this.medicinaService.getMedicinaID(medicina.medicina_id).subscribe(res =>{
                this.medicina = res;
                console.log("Funcion OpenModalEdit");
                console.log(this.medicina);
            });
        }
    }


}
