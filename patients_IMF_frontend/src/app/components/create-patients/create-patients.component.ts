import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { patientsModel } from '../patients-list/patientsModel';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-create-patients',
  templateUrl: './create-patients.component.html',
  styleUrls: ['./create-patients.component.css']
})
export class CreatePatientsComponent implements OnInit {

  patient: patientsModel = new patientsModel()
  fg: FormGroup
  submitted = false
  fileToUpload: any;
  
  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient, private toastr: ToastrService){
  this.fg = this.fb.group({
        patientName: ['', [Validators.required]],
        gender: ['', [Validators.required]],
        age: ['', [Validators.required]],
        dob: ['', [Validators.required]],
        phoneNumber: ['', [Validators.required, Validators.pattern("[0-9]{11}")]],
        email: ['', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')]],
        address: ['', [Validators.required]],
        photoUrl: ['', [Validators.required]], 
  }
 
    )
}

get pstnValid() {
  return this.fg.controls;
}

isShowTable: boolean = false;
isSave: boolean = true;

  ngOnInit(): void {
  }
  fileChange(files: any) {
    
    this.fileToUpload = files.files[0]
  }





  savePatient() {
    console.log(this.fg.value);

    this.submitted = true;
       
    const formData: FormData = new FormData();

     
    formData.append('patientName',this.fg.get('patientName')?.value);
    formData.append('gender',this.fg.get('gender')?.value);
    formData.append('age',this.fg.get('age')?.value);
    formData.append('dob', new Date(this.fg.get('dob')?.value).toUTCString());
    formData.append('phoneNumber',this.fg.get('phoneNumber')?.value);
    formData.append('email',this.fg.get('email')?.value);
    formData.append('address',this.fg.get('address')?.value);
    formData.append('file', this.fileToUpload, this.fileToUpload?.name);
    console.log(formData);

    const headers = { 'content-Type': 'application/json' };
    this.http.post<any>("http://localhost:8081/patient/save", formData)
      .subscribe(data => {
        console.log(data);
      this.toastr.success("save successfull");
      },err => {
        console.log("error");
        this.toastr.success("save failed");
      }
      )
    //this.router.navigate(["/admin/show"]);

  }

  

}
