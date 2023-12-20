export class Medicina{
  constructor(medicinaId='',sintomas='',diagnostico='' ,tratamiento='',fecha_vacuna='',ganado_id=''){
    this.medicinaId = medicinaId;
    this.sintomas = sintomas;
    this.diagnostico = diagnostico;
    this.tratamiento = tratamiento;
    this.fecha_vacuna = fecha_vacuna;
    this.ganado_id = ganado_id;
  }
  medicinaId: string;
  sintomas: string;
  diagnostico: string;
  tratamiento: string;
  fecha_vacuna: string;
  ganado_id: string;
}
