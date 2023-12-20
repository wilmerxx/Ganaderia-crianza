export class Reproduccion {
  reproduccion_id: string;
  fecha_parto: string;
  estado_salud: string;
  numero_crias: string;
  ganado_id: string;

  constructor(
    reproduccion_id: string = '',
    fecha_parto: string = '',
    estado_salud: string = '',
    numero_crias: string = '',
    ganado_id: string = ''
  ) {
    this.reproduccion_id = reproduccion_id;
    this.fecha_parto = fecha_parto;
    this.estado_salud = estado_salud;
    this.numero_crias = numero_crias;
    this.ganado_id = ganado_id;
  }
}
