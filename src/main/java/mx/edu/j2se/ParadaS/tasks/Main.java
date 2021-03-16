package mx.edu.j2se.ParadaS.tasks;

public class Main {
	
	public static void main(String[] args) {

		System.out.println("Hola, esta es una prueba");
		System.out.println("Creamos una objeto de Task no repetitiva:");
		Task norep = new Task("No repetitiva", 10);
		System.out.println("Nombre: " + norep.getTitle() + "\nTiempo: " + norep.getTime());
		System.out.println("Creamos una tarea repetitiva:");
		Task rep = new Task("Repetitiva", 10, 24, 2);
		System.out.println("Nombre: " + rep.getTitle() + "\nInicio: " + rep.start + "\nFinal: " + rep.end + "\nIntervalo: " + rep.interval);
		System.out.println("Guardamos en arreglo");
		ArrayTaskList lista = new ArrayTaskList();
		lista.add(norep);
		System.out.println("Imprimimos nombre del primer task de la lista y sus parametros: ");
		System.out.println("Nombre: " + lista.getTask(1).getTitle() + "\nInicio: " + lista.getTask(1).start + "\nFinal: " + lista.getTask(1).end + "\nIntervalo: " +
				lista.getTask(1).interval + "\nTiempo:" + lista.getTask(1).time);
		System.out.println("La lista tiene " + lista.size() + " tarea guardada");
		lista.add(rep);
		System.out.println("Imprimimos nombre del segundo task de la lista y sus parametros: ");
		System.out.println("Nombre: " + lista.getTask(2).getTitle() + "\nInicio: " + lista.getTask(2).start + "\nFinal: " + lista.getTask(2).end + "\nIntervalo: " +
				lista.getTask(2).interval + "\nTiempo:" + lista.getTask(2).time);
		System.out.println("La lista tiene " + lista.size() + " tareas guardada");
		System.out.println("Guardamos 5 veces tarea repetitiva y no repetitiva");
		for(int i= 0;i<10; i++){
			if(i % 2 == 0){
				lista.add(norep);
			}
			else{
				lista.add(rep);
			}
		}
		System.out.println("Los nombres de las tareas guardadas son:");
		for(int i = 0;i<lista.size();i++){
			System.out.println("la tarea "+(i+1)+" se llama: "+lista.getTask(i+1).getTitle());
		}
		System.out.println("Removemos todas las no repetitivas e imprimimos nuevamente: ");
		lista.remove(norep);
		System.out.println("Los nombres de las tareas guardadas despues del remove son:");
		for(int i = 0;i<lista.size();i++){
			System.out.println("la tarea "+(i+1)+" se llama: "+lista.getTask(i+1).getTitle());
		}
		Task noGuardado = new Task("Este no se guarda", 8);
		System.out.println("Borrar task que no existe: "+lista.remove(noGuardado));
		System.out.println("El nombre del Task 6 es: "+lista.getTask(6).getTitle());
		try {
			System.out.println("Y el nombre del Task 7 es: ");
			System.out.println(lista.getTask(7).getTitle());
		}catch(IndexOutOfBoundsException e){
			System.out.println("No existió el Task");
		}
		System.out.println("Ahora buscamos Tasks en un rango apropiado");
		System.out.println("Hay "+lista.incoming(8,30).size()+" tareas");
		System.out.println("Ahora buscamos Tasks en un rango incorrecto");
		try {
			lista.incoming(30, 8).size();
		}catch(IllegalArgumentException e){
			System.out.println("UPS rango invalido");
		}
		System.out.println("Ahora buscamos Tasks en un rango que no coincida con las tareas");
		System.out.println("Hay "+lista.incoming(40,45).size()+" tareas");
		System.out.println("Repetimos para LinkedList:");
		///////////////////////////////////////////////////
		LinkedTaskList lista2 = new LinkedTaskList();
		lista2.add(norep);
		System.out.println("Imprimimos nombre del primer task de la lista y sus parametros: ");
		System.out.println("Nombre: " + lista2.getTask(1).getTitle() + "\nInicio: " + lista2.getTask(1).start + "\nFinal: " + lista2.getTask(1).end + "\nIntervalo: " +
				lista2.getTask(1).interval + "\nTiempo:" + lista2.getTask(1).time);
		System.out.println("La lista tiene " + lista2.size() + " tarea guardada");
		lista2.add(rep);
		System.out.println("Imprimimos nombre del segundo task de la lista y sus parametros: ");
		System.out.println("Nombre: " + lista2.getTask(2).getTitle() + "\nInicio: " + lista2.getTask(2).start + "\nFinal: " + lista2.getTask(2).end + "\nIntervalo: " +
				lista2.getTask(2).interval + "\nTiempo:" + lista2.getTask(2).time);
		System.out.println("La lista tiene " + lista2.size() + " tareas guardada");
		System.out.println("Guardamos 5 veces tarea repetitiva y no repetitiva");
		for(int i= 0;i<10; i++){
			if(i % 2 == 0){
				lista2.add(norep);
			}
			else{
				lista2.add(rep);
			}
		}

		System.out.println("Los nombres de las tareas guardadas son:");
		for(int i = 0;i<lista2.size();i++){
			System.out.println("la tarea "+(i+1)+" se llama: "+lista2.getTask(i+1).getTitle());
		}
		System.out.println("Removemos todas las no repetitivas e imprimimos nuevamente: ");
		lista2.remove(norep);
		System.out.println("Los nombres de las tareas guardadas despues del remove son:");
		for(int i = 0;i<lista2.size();i++){
			System.out.println("la tarea "+(i+1)+" se llama: "+lista2.getTask(i+1).getTitle());
		}
		//////////////////////////////////////////////////////////
		System.out.println("Borrar task que no existe: "+lista2.remove(noGuardado));
		System.out.println("El nombre del Task 6 es: "+lista2.getTask(6).getTitle());
		try {
			System.out.println("Y el nombre del Task 7 es: ");
			System.out.println(lista2.getTask(7).getTitle());
		}catch(IndexOutOfBoundsException e){
			System.out.println("No existió el Task");
		}
		System.out.println("Ahora buscamos Tasks en un rango apropiado");
		System.out.println("Hay "+lista2.incoming(8,30).size()+" tareas");
		System.out.println("Ahora buscamos Tasks en un rango incorrecto");
		try {
			lista2.incoming(30, 8).size();
		}catch(IllegalArgumentException e){
			System.out.println("UPS rango invalido");
		}
		System.out.println("Ahora buscamos Tasks en un rango que no coincida con las tareas");
		System.out.println("Hay "+lista2.incoming(40,45).size()+" tareas");


	}
	
}
