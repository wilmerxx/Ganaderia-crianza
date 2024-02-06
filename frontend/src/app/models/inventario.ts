export class Inventario{
  id_producto?: number;
  cantidad_producto?: number;
  fecha_inventario?: string;
  constructor( ) {
    this.id_producto = 0;
    this.cantidad_producto = 0;
    this.fecha_inventario = '';
  }
}
