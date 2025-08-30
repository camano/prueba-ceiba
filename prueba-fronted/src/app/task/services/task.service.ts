import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../models/Task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  URLBACKEND = "http://localhost:9000/tasks";

  constructor(private httpClient: HttpClient) { }

  getTaskAll():Observable<Task[]>{
    return this.httpClient.get<Task[]>(this.URLBACKEND);
  }

  getTaskId(id:number):Observable<Task>{
    return this.httpClient.get<Task>(this.URLBACKEND + "/" + id);
  }

  getTaskEstado(estado:string):Observable<Task[]>{
    return this.httpClient.get<Task[]>(this.URLBACKEND + "/estado/"+estado);
  }

  addTask(taskNew:Task):Observable<any>{
    return this.httpClient.post<Task>(this.URLBACKEND,taskNew);
  }

  updateTask(taskNew:Task,id:number):Observable<any>{
    return this.httpClient.put<Task>(this.URLBACKEND + "/"+id,taskNew);
  }

  deleteTask(id:number):Observable<any>{
    return this.httpClient.delete<Task>(this.URLBACKEND + "/"+id);
  }
}
