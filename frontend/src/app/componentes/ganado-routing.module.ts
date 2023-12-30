import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GanadoRegistroComponent } from './ganado-registro/ganado-registro.component';
import { GanadoComponent } from './ganado.component';
import { ReproduccionComponent } from './reproduccion/reproduccion.component';
import { AlimentacionComponent } from './alimentacion/alimentacion.component';
import { AreaComponent } from './area/area.component';
import { EnfermedadesComponent } from './enfermedades/enfermedades.component';
import { MedicinaComponent } from './medicina/medicina.component';
import { InicioComponent } from './inicio/inicio.component';

const routes: Routes = [
  {path: 'ganado',
  component: GanadoComponent,
  children: [
    { path: 'inicio', component: InicioComponent},
    { path: 'ganado-registro', component: GanadoRegistroComponent},
    { path: 'reproduccion', component: ReproduccionComponent},
    { path: 'alimentacion', component: AlimentacionComponent},
    { path: 'area', component: AreaComponent},
    { path: 'enfermedades', component: EnfermedadesComponent},
    { path: 'medicina', component: MedicinaComponent},
    { path: '', redirectTo: 'inicio', pathMatch: 'full'},
  ],},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class GanadoRoutingModule { }
