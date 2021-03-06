import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { patientsModel } from './patientsModel';

@Component({
  selector: 'app-patients-list',
  templateUrl: './patients-list.component.html',
  styleUrls: ['./patients-list.component.css']
})
export class PatientsListComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  patientList: any
  patient: patientsModel = new patientsModel()
  
  
  ngOnInit(): void {
    this.getPatients()
  }

  header = { 'Content-Type': 'application/json' };
  getPatients(){
    console.log("hi patients");
    
    const headers   = { 'content-type': 'application/json' }
    this.http.get<any>("http://localhost:8081/patient/list", {headers: headers})
    .subscribe(map=>{
      console.log(map.data);
      this.patientList = map.data
    })
  }

  delete(id:string){
    this.http.post("http://localhost:8081/patient/delete/"+id, JSON.stringify(this.patient), { headers: this.header }).subscribe(res => {
      console.log(res);
      this.getPatients()

    })
  }

}
