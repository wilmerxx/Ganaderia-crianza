export class Medicina {
  medicina_id: string;
  sintomas: string;
  diagnostico: string;
  tratamiento: string;
  fecha_vacuna: string;
  ganado_id: string;

  constructor(
    medicina_id: string = "",
    sintomas: string = "",
    diagnostico: string = "",
    tratamiento: string = "",
    fecha_vacuna: string = "",
    ganado_id: string = ""
  ) {
    this.medicina_id = medicina_id;
    this.sintomas = sintomas;
    this.diagnostico = diagnostico;
    this.tratamiento = tratamiento;
    this.fecha_vacuna = fecha_vacuna;
    this.ganado_id = ganado_id;
  }
}
