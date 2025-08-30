import { Component, OnInit } from '@angular/core';
import { TaskService } from '../services/task.service';
import { Task } from '../models/Task';
import { Estado } from '../models/Estado';

@Component({
  selector: 'app-component',
  templateUrl: './component.component.html',
  styleUrls: ['./component.component.css']
})
export class ComponentComponent implements OnInit {

public tasks:Task[]=[];
query: string = '';
descripcion:string='';

estados:Estado={
  estadoId:0,
  nombreEstado:""
}
taskNew:Task={
  descripcion:this.descripcion,
  estado:this.estados,
  fechaCreacion:"",
  taskId:0


}




  constructor(private taskService:TaskService){}

  ngOnInit(): void {
    this.tasksAll();
      
  }

  tasksAll():void{
    this.taskService.getTaskAll().subscribe(
      (res)=>{
        this.tasks=res
      },
      (error)=>{
        console.log("Error en la lista de tarea")
      }
    );
  }

  taskId():void{
    this.taskService.getTaskId(2).subscribe(
      (res)=>{

      },
      (er)=>{
        console.log("Error al consultar la tarea");
      }
    );
  }

  taskEstado():void{
    
  }

  buscar() {
    if (this.query) {
      this.taskService.getTaskEstado(this.query).subscribe(
        (res)=>{
          this.tasks=res;
        }
      );
    } else {
      this.tasksAll();
    }
  }

  eliminarId(id:number){
    this.taskService.deleteTask(id).subscribe(
      (res)=>{
        alert("Se elimino la tarea "+ id)
        this.tasksAll();
      }
    );
  }

  addtask(){
    this.taskNew.descripcion=this.descripcion
     this.taskService.addTask(this.taskNew).subscribe(
      (res)=>{
        alert("Se agrego una nueva tarea")
        this.tasksAll();
      }
    ) 
  }

}
