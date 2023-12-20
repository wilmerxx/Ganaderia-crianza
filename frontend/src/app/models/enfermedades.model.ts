export class Enfermedad {
  control_id: string | undefined;
  tipo_control: string | undefined;
  pesoActual: number | undefined;
  fechaControl: string | undefined;
  observaciones: string | undefined;
  ganado_id: string | undefined;
  estado: string | undefined;

  constructor(
    control_id = '',
    tipo_control = '',
    pesoActual = 0.0,
    fechaControl = '',
    observaciones = '',
    ganado_id = '',
    estado = ''
  ) {
    this.control_id = control_id;
    this.tipo_control = tipo_control;
    this.pesoActual = pesoActual;
    this.fechaControl = fechaControl;
    this.observaciones = observaciones;
    this.ganado_id = ganado_id;
    this.estado = estado;
  }
}
