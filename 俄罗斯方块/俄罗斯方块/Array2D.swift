//
//  Array2D.swift
//  俄罗斯方块
//
//  Created by s20151104684 on 18/5/11.
//  Copyright © 2018年 s20151104684. All rights reserved.
//

class Array2D<T> {
    let colums:Int
    let rows:Int
    
    var array:Array<T?>
    
    init(colums:Int,rows:Int){
        self.colums=colums
        self.rows=rows
        
        array=Array<T?>(repeating:nil,count:rows*colums)
    }
    subscript(column:Int,row:Int)->T?{
        get {
            return array[(row*colums)+column]
        }
        set(newValue){
            array[(row*colums)+column]=newValue
        }
    }
    
    
}
