export class Area {
  constructor(areaId= '', nombreArea='', tipoArea='', tipoPasto='', superficie= 0.0, ganado_id=''){
    this.areaId = areaId;
    this.nombreArea = nombreArea;
    this.tipoArea = tipoArea;
    this.tipoPasto = tipoPasto;
    this.superficie = superficie;
    this.ganado_id = ganado_id;
  }
  areaId: string;
  nombreArea: string;
  tipoArea: string;
  tipoPasto: string;
  superficie:number;
  ganado_id: string;
}
