package Modele;

import java.util.ArrayList;

public class ArrayVertex implements Cloneable{

	protected ArrayList<Vertex3D>list;
	
	public ArrayVertex(){
		list=new ArrayList<Vertex3D>();
	}
	
	public Vertex3D get(int i){
		return list.get(i);
	}
	
	public void add(Vertex3D vertex3d){
		list.add(vertex3d);
	}
	
	public void clear(){
		list.clear();
	}
	
	public int size(){
		return list.size();
	}
	
	public Object clone() throws CloneNotSupportedException{
		ArrayVertex array=(ArrayVertex) super.clone();
		array.list=new ArrayList<Vertex3D>();
		for(Vertex3D i:this.list){
			array.list.add((Vertex3D) i.clone());
		}
		return array;
	}
}
