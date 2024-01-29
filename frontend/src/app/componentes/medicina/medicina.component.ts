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
            if (formData.ganado) {
                const ganado = this.ganadoService.ganados.find(g => g.ganado_id === String(formData.ganado.ganado_id));
                if (ganado) {
                    const formData = this.form.value;
                    formData.ganado_id = ganado.ganado_id || undefined;
                    formData.ganado_nombre = ganado.nombre_ganado || undefined;

                    this.medicinaService.postMedicina(formData).subscribe(
                        (res) => {
                            console.log('Respuesta del servidor:', res);
                            this.closeModal();
                            this.form.reset();
                            this.getMedicinas();
                        },
                        (error) => {
                            console.error('Error al guardar:', error);
                        }
                    );
                } else {
                    console.error('Error: El ganado seleccionado no existe en la base de datos');
                }
            } else {
                console.log('Ganado no seleccionado en el formulario');
            }
        } else {
            console.log('Formulario no válido');
        }
    }

    getError(controlName: string) {
        const control = this.form.get(controlName);
        return control?.hasError('required') ? `El ${controlName} es requerido` : '';
    }

    getCurrentDate() {
        return new Date().toISOString().split('T')[0];
    }

    limpiarFormulario(form: NgForm) {
        form.reset();
    }

    getMedicinas() {
        this.medicinaService.getMedicinas()
            .pipe(
                catchError((error) => {
                    console.error('Error al obtener las medicinas:', error);
                    // Puedes mostrar un mensaje de error al usuario según tus necesidades
                    throw error; // Propagar el error para que pueda ser manejado por la suscripción externa si es necesario
                })
            )
            .subscribe((res) => {
                this.medicinaService.medicinas = res as Medicina[];
                this.getGanados();
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
                console.log(res);
            });
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

    openModalEdit(medicina: Medicina) {
        if (this.exampleModalEdit) {
            const modalElement = this.exampleModalEdit.nativeElement;
            modalElement.classList.add('show');
            modalElement.style.display = 'block';

            if (medicina.medicinaId) {
                this.medicinaService.getMedicinaID(medicina.medicinaId)
                    .pipe(
                        catchError((error) => {
                            console.error('Error al obtener la medicina:', error);
                            throw error;
                        })
                    )
                    .subscribe((res) => {
                        this.medicina = res;
                        console.log("Funcion OpenModalEdit");
                        console.log(this.medicina);
                    });
            } else {
                console.error('Error: medicina.medicinaId es undefined');
            }
        }
    }

    deleteMedicina(medicina: Medicina | undefined) {
        if (medicina && medicina.medicinaId) {
            this.medicinaService.deleteMedicina(medicina.medicinaId)
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



}
