export class Medicina{
  constructor(medicinaId='',nombre_ganado='',sintomas='',diagnostico='' ,tratamiento='',fecha_vacuna='',ganado_id=''){
    this.medicinaId = medicinaId;
    this.nombre_ganado = nombre_ganado;
    this.sintomas = sintomas;
    this.diagnostico = diagnostico;
    this.tratamiento = tratamiento;
    this.fecha_vacuna = fecha_vacuna;
    this.ganado_id = ganado_id;
  }
  medicinaId: string;
  nombre_ganado: string;
  sintomas: string;
  diagnostico: string;
  tratamiento: string;
  fecha_vacuna: string;
  ganado_id: string;
}
