export class Medicina {
  medicina_id?: string;
  ganado_id: number;
  nombreGanado?: string;
  nombre_ganado?: string;
  codigoGanado?: string;
  sintomas?: string;
  diagnostico?: string;
  tratamiento?: string;
  fecha_vacuna?: string;

  constructor() {
    this.medicina_id = "";
    this.sintomas = "";
    this.diagnostico = "";
    this.tratamiento = "";
    this.fecha_vacuna = "";
    this.ganado_id = 0;
  }
}
