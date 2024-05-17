/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.generic;

/**
 *
 * @author ASUS
 * @param <T>
 */
public class myData <T>{
private T data;

public myData(T data){
    this.data=data;
}

public T getData(){
    return data;
}

public void setData(T data){
    this.data=data;
}
  
}
