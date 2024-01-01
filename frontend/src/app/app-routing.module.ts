import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {InicioComponent} from "./componentes/inicio/inicio.component";
import {GanadoRegistroComponent} from "./componentes/ganado-registro/ganado-registro.component";
import {ReproduccionComponent} from "./componentes/reproduccion/reproduccion.component";
import {AlimentacionComponent} from "./componentes/alimentacion/alimentacion.component";
import {AreaComponent} from "./componentes/area/area.component";
import {EnfermedadesComponent} from "./componentes/enfermedades/enfermedades.component";
import {MedicinaComponent} from "./componentes/medicina/medicina.component";

const routes: Routes = [
  {path: '', component: InicioComponent},
  {path: 'inicio', component: InicioComponent},
  {path: 'ganado', component: GanadoRegistroComponent},
  {path: 'reproduccion', component: ReproduccionComponent},
  {path: 'alimentacion', component: AlimentacionComponent},
  {path: 'area', component: AreaComponent},
  {path: 'enfermedades', component: EnfermedadesComponent},
  {path: 'medicina', component: MedicinaComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class GanadoRoutingModule { }
