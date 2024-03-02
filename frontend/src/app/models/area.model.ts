export class Area {
  areaId?: number;
  ganado_id: number;
  nombreGanado?: string;
  nombre_ganado?: string;
  codigoGanado?: string;
  nombreArea?: string;
  tipoArea?: string;
  tipoPasto?: string;
  superficie?:number;

  constructor() {
    this.areaId = 0;
    this.nombreArea = "";
    this.tipoArea = "";
    this.tipoPasto = "";
    this.superficie = 0;
    this.ganado_id = 0;
  }
}
