import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePatientsComponent } from './components/create-patients/create-patients.component';
import { HomeComponent } from './components/home/home.component';
import { PatientsListComponent } from './components/patients-list/patients-list.component';

const routes: Routes = [
  { path: "", component:HomeComponent, children: [
    {path: "patientlist", component: PatientsListComponent},
    {path: "createpatient", component: CreatePatientsComponent}
  ] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
