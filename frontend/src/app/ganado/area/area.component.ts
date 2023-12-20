import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AreaService} from "../../area.service";
import {Area} from "../../models/area.model";
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-area',
  templateUrl: './area.component.html',
  styleUrls: ['./area.component.css']
})
export class AreaComponent implements OnInit {
  validador: boolean;
  constructor(public areaService: AreaService) {
    this.validador = false;
  }
  ngOnInit(): void {
    this.getAreas();
  }

  areaData: any[] = [
    { nombre: 'La palmera',tipo_terreno:'plano', tipo_pasto: 'Gramalote', superficie: '3 hectareas',total_ganado:'23' },

  ];

  editingRow: number | null = null;

  @ViewChild('exampleModal') exampleModal!: ElementRef;

  getAreas(){
    this.areaService.getAreas().subscribe((res) =>{
      this.areaService.areas = res as Area[];
      console.log(res);
    })
  }


  openModal() {
    if (this.exampleModal) {
      const modalElement = this.exampleModal.nativeElement;
      modalElement.classList.add('show');
      modalElement.style.display = 'block';
    }
  }
  closeModal() {
    if (this.exampleModal) {
      const modalElement = this.exampleModal.nativeElement;
      modalElement.classList.remove('show');
      modalElement.style.display = 'none';
    }
  }
  startEditing(rowId: number) {
    this.editingRow = rowId;
  }

  stopEditing() {
    this.editingRow = null;
  }

  isEditing(rowId: number): boolean {
    return this.editingRow === rowId;
  }



}
