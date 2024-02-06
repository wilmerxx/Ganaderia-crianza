export class Producto{
  id?: number;
  nombreproducto?: string;
  unidad_medida?: string;
  marca?: string;
  tipo_producto?: string;
  fecha_vencimiento?: string;
  constructor() {
    this.id = 0;
    this.nombreproducto = "";
    this.unidad_medida = "";
    this.marca = "";
    this.tipo_producto = "";
    this.fecha_vencimiento = "";
  }
}
