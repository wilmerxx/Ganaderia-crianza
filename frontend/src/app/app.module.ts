import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavegacionComponent } from './navegacion/navegacion.component';
import { InicioComponent } from './componentes/inicio/inicio.component';
import { AlimentacionComponent } from './componentes/alimentacion/alimentacion.component';
import { AreaComponent } from './componentes/area/area.component';
import { EnfermedadesComponent } from './componentes/enfermedades/enfermedades.component';
import { GanadoRegistroComponent } from './componentes/ganado-registro/ganado-registro.component';
import { MedicinaComponent } from './componentes/medicina/medicina.component';
import { ReproduccionComponent } from './componentes/reproduccion/reproduccion.component';
import { GanadoRoutingModule } from './app-routing.module';
import { NgChartsModule} from 'ng2-charts';
import { NgxChartsModule} from "@swimlane/ngx-charts";
import { BodegaComponent } from './componentes/producto/bodega.component';
import { ConsumoComponent } from './componentes/inventario/consumo.component';
import {HighchartsChartModule} from "highcharts-angular";
import {NgbAccordionModule,NgbAccordionDirective,NgbAccordionItem,NgbAccordionHeader, NgbAccordionToggle, NgbAccordionCollapse, NgbAccordionBody, NgbAccordionButton,} from "@ng-bootstrap/ng-bootstrap";
import { LoginComponent } from './componentes/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    NavegacionComponent,
    InicioComponent,
    AlimentacionComponent,
    AreaComponent,
    EnfermedadesComponent,
    GanadoRegistroComponent,
    MedicinaComponent,
    ReproduccionComponent,
    BodegaComponent,
    ConsumoComponent,
    LoginComponent

    ],
    imports: [
        BrowserModule,
        RouterModule,
        NgbModule,
        FormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        GanadoRoutingModule,
        NgChartsModule,
        NgxChartsModule,
        ReactiveFormsModule,
        HighchartsChartModule,
        NgbAccordionModule,
        NgbAccordionDirective,
        NgbAccordionItem,
        NgbAccordionHeader,
        NgbAccordionToggle,
        NgbAccordionCollapse,
        NgbAccordionBody,

    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
