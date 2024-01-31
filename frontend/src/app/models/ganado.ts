import {Area} from "./area.model";

export class Ganado {
  ganado_id?: string;
  codigo?: string ;
  nombre_ganado?: string;
  raza?: string ;
  peso?: number;
  sexo?: string;
  edad?: number;
  fechaNacimiento?: string;
  ganado_madre_id?: string ;
  ganado_padre_id?: string;
  estado?: string;
  nombre_madre?: string;
  nombre_padre?: string;

  constructor(){
    this.ganado_id = "";
    this.codigo = "";
    this.nombre_ganado = "";
    this.raza = "";
    this.peso = 0;
    this.sexo = "";
    this.fechaNacimiento = "";
    this.nombre_madre = "";
    this.nombre_padre = "";
    this.estado = "";
  }
}
