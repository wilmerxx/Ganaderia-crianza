export class Reproduccion {
  reproduccion_id?: string;
  ganado_id: number;
  nombreGanado?: string;
  nombre_ganado?: string;
  codigoGanado?: string;
  fecha_parto?: string;
  estado_parto?: string;
  observaciones?: string;
  numero_crias?: number;

  constructor() {
    this.reproduccion_id = "";
    this.fecha_parto= "";
    this.estado_parto= "";
    this.observaciones= "";
    this.numero_crias= 0;
    this.ganado_id=0;
  }
}
