import {Area} from "./area.model";

export class Ganado {
  ganado_id?: number;
  codigo?: string ;
  nombre_ganado?: string;
  raza?: string ;
  peso?: number;
  sexo?: string;
  edad?: number;
  tipo?: string;
  fechaNacimiento: string;
  ganado_madre_id?: string ;
  ganado_padre_id?: string;
  estado?: string;
  nombre_madre?: string;
  nombre_padre?: string;

  constructor(){
    this.ganado_id = 0;
    this.codigo = "";
    this.nombre_ganado = "";
    this.raza = "";
    this.peso = 0;
    this.sexo = "";
    this.fechaNacimiento = "";
    this.ganado_madre_id = "";
    this.ganado_madre_id = "";
  }
}
