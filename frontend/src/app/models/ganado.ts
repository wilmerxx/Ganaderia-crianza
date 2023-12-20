import {Area} from "./area.model";

export class Ganado {

  /*
      {
        "ganado_id": "c54623d0-ad13-419c-9ad5-5fc0ab8d8db3",
        "codigo": "",
        "nombre_ganado": "torivio",
        "raza": "",
        "peso": 0.0,
        "sexo": "String sexo",
        "fechaNacimiento": "19-12-2023",
        "tipo": "Toro",
        "madre_id": null,
        "padre_id": null,
        "estado": "",

    }
   */
  ganado_id: string | undefined;
  codigo: string | undefined;
  nombre_ganado: string | undefined;
  raza: string | undefined;
  peso: number | undefined;
  sexo: string | undefined;
  fechaNacimiento: string | undefined;
  tipo: string | undefined;
  madre_id: string | undefined;
  padre_id: string | undefined;
  estado: string | undefined;

  public Ganado(ganado_id = '', codigo = '', nombre_ganado = '', raza = '', peso = 0.0, sexo = '', fechaNacimiento = '', tipo = '', madre_id = '', padre_id = '', estado = ''){
    this.ganado_id = ganado_id;
    this.codigo = codigo;
    this.nombre_ganado = nombre_ganado;
    this.raza = raza;
    this.peso = peso;
    this.sexo = sexo;
    this.fechaNacimiento = fechaNacimiento;
    this.tipo = tipo;
    this.madre_id = madre_id;
    this.padre_id = padre_id;
    this.estado = estado;

  }



}
