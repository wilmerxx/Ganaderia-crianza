import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GanadoComponent } from './ganado/ganado.component';

const routes: Routes = [
  { path: 'ganado', component: GanadoComponent },
  { path: '', pathMatch: 'full', redirectTo: 'ganado' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
