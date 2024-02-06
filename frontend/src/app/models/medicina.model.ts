export class Medicina {
  medicina_id?: number;
  ganado_id: number;
  nombreGanado?: string;
  nombre_ganado?: string;
  codigoGanado?: string;
  sintomas?: string;
  diagnostico?: string;
  tratamiento?: string;
  fecha_vacuna?: string;
  estado?: string;
  creado?:string;
  modificado?:string;

  constructor() {
    this.medicina_id = 0;
    this.sintomas = "";
    this.diagnostico = "";
    this.tratamiento = "";
    this.fecha_vacuna = "";
    this.ganado_id = 0;
  }
}
