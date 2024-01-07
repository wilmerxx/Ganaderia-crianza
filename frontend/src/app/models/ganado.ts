import {Area} from "./area.model";

export class Ganado {
  ganado_id?: string;
  codigo?: string ;
  nombre_ganado?: string;
  raza?: string ;
  peso?: number;
  sexo?: string;
  fechaNacimiento?: string;
  tipo?: string;
  madre_id?: string ;
  padre_id?: string;
  estado?: string;
  nombreMadre?: string;
  nombrePadre?: string;

  public Ganado(){
    this.ganado_id = "";
    this.codigo = "";
    this.nombre_ganado = "";
    this.raza = "";
    this.peso = 0;
    this.sexo = "";
    this.fechaNacimiento = "";
    this.tipo = "";
    this.madre_id = "";
    this.padre_id = "";
    this.estado = "";
  }
}
