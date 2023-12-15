import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GanadoRegistroComponent} from "./ganado/ganado-registro/ganado-registro.component";

const routes: Routes = [
  {path: '', component: GanadoRegistroComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
