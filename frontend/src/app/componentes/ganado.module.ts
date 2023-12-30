import { NgModule } from '@angular/core';
import { GanadoRegistroComponent } from './ganado-registro/ganado-registro.component';
import { GanadoComponent } from './ganado.component';
import { CommonModule } from '@angular/common';
import { GanadoRoutingModule } from './ganado-routing.module';
import { ReproduccionComponent } from './reproduccion/reproduccion.component';
import { AlimentacionComponent } from './alimentacion/alimentacion.component';
import { AreaComponent } from './area/area.component';
import { EnfermedadesComponent } from './enfermedades/enfermedades.component';
import { MedicinaComponent } from './medicina/medicina.component';
import { InicioComponent } from './inicio/inicio.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    GanadoComponent,
    GanadoRegistroComponent,
    ReproduccionComponent,
    AlimentacionComponent,
    AreaComponent,
    EnfermedadesComponent,
    MedicinaComponent,
    InicioComponent
  ],
  imports: [
    CommonModule,
    GanadoRoutingModule,
    FormsModule
  ],
  providers: [],
  exports: [
    GanadoComponent
  ],
  bootstrap: [GanadoComponent]
})
export class GanadoModule { }
