import { Estado } from "./Estado";

export interface Task {

    taskId:number;
    descripcion:string;
    fechaCreacion:string;
    estado:Estado
}