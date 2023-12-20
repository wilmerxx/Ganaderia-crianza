export class Alimentacion {
  constructor(alimentacion_id='',nombre_suplemento='',cantidad_suplemento='' ,fecha_alimentacion='',ganado_id=''){
    this.alimentacion_id = alimentacion_id;
    this.nombre_suplemento = nombre_suplemento;
    this.cantidad_suplemento = cantidad_suplemento;
    this.fecha_alimentacion = fecha_alimentacion;
    this.ganado_id = ganado_id;
  }
  alimentacion_id: string;
  nombre_suplemento: string;
  cantidad_suplemento: string;
  fecha_alimentacion: string;
  ganado_id: string;
}
