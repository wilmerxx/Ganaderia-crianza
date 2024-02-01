export class Enfermedad {
  control_id?: string;
  ganado_id: number;
  nombreGanado?: string;
  nombre_ganado?: string;
  codigoGanado?: string;
  tipo_control: string;
  pesoActual: number;
  fechaControl: string;
  observaciones: string;
  estado?: string;
  creado:string;
  modificado:string;

  constructor() {
    this.control_id = "";
    this.ganado_id = 0;
    this.nombre_ganado = "";
    this.codigoGanado= "";
    this.tipo_control = "";
    this.pesoActual = 0;
    this.fechaControl = "";
    this.observaciones = "";
    this.creado = "";
    this.modificado = "";
  }
}
