
export class Medicina {
  medicinaId?: string;
  ganado?: string; // Cambiar de Ganado a string
  sintomas?: string;
  diagnostico?: string;
  tratamiento?: string;
  fecha_vacuna?: string;

  public Medicina(){
    this.medicinaId="";
    this.ganado="";
    this.sintomas="";
    this.diagnostico="";
    this.tratamiento="";
    this.fecha_vacuna="";
  }
}
