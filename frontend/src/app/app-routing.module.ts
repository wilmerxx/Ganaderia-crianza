import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GanadoComponent } from './componentes/ganado.component';

const routes: Routes = [
  { path: 'ganado', component: GanadoComponent },
  { path: '', redirectTo: 'ganado/inicio', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
