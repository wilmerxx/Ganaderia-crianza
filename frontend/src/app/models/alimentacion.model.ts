export class Alimentacion {

  alimentacion_id?: number | undefined;
  ganado_id: number;
  nombre_suplemento?: string;
  cantidad_suplemento: number;
  fecha_alimentacion?: string;
  codigo?: string;
  nombre_ganado?: string;
  creado?:string;
  modificado?:string;

    constructor() {
      this.nombre_suplemento = "";
      this.cantidad_suplemento = 0;
      this.fecha_alimentacion = "";
      this.ganado_id = 0;
    }
}
