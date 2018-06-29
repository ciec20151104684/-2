//
//  GameViewController.swift
//  ltx
//
//  Created by s20151104684 on 18/6/26.
//  Copyright © 2018年 s20151104684. All rights reserved.
//

import UIKit
import SpriteKit
import GameplayKit

class GameViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let view = self.view as! SKView? {
            // Load the SKScene from 'GameScene.sks'
            if let scene = SKScene(fileNamed: "GameScene") {
                // Set the scale mode to scale to fit the window
                scene.scaleMode = .aspectFill
                
                // Present the scene
                view.presentScene(scene)
            }
            
            view.ignoresSiblingOrder = true
            
            view.showsFPS = true
            view.showsNodeCount = true
        }
    }
    // MARK: 绘制俄罗斯方库网格的方法
    func creatcells(rows:Int,cols:Int,cellwidth:Int,cellHeight:Int) -> Void {
        
        // 开始创建路径
        CGContextBeginPath(CTX)
        // 绘制横向网格对应的路径
        for  i  in 0...TETRIS_Row {
            
            CGContextMoveToPoint(CTX, 0, CGFloat(i  *  CELL_Size))
            CGContextAddLineToPoint(CTX, CGFloat(TETRIS_Cols * CELL_Size), CGFloat(i * CELL_Size))
            
        }
        // 绘制纵向的网格对应路径
        for  i  in 0...TETRIS_Cols {
            
            CGContextMoveToPoint(CTX, CGFloat(i  *  CELL_Size),0)
            CGContextAddLineToPoint(CTX, CGFloat(i * CELL_Size), CGFloat(TETRIS_Row * CELL_Size))
            
        }
        // 关闭
        CGContextClosePath(CTX)
        
        // 设置笔触颜色
        CGContextSetStrokeColorWithColor(CTX, UIColor(red: 0.9 , green: 0.9 , blue: 0.9,alpha: 1).CGColor)
        // 设置效线条粗细
        CGContextSetLineWidth(CTX, CGFloat(STROKE_Width))
        // 绘制线条
        CGContextStrokePath(CTX)
        
    }

    // 几种可能的组合方块
    self.blockArr = [
    
    // 第一种可能出现的组合 Z
    [
    Block(X:TETRIS_Cols/2 - 1,Y:0,Color:1),
    Block(X:TETRIS_Cols/2,Y:0,Color:1),
    Block(X:TETRIS_Cols/2,Y:1,Color:1),
    Block(X:TETRIS_Cols/2 + 1,Y:1,Color:1)
    
    ],
    // 第二种可能出现的组合 反Z
    [
    Block(X:TETRIS_Cols/2 + 1,Y:0,Color:2),
    Block(X:TETRIS_Cols/2,Y:0,Color:2),
    Block(X:TETRIS_Cols/2,Y:1,Color:2),
    Block(X:TETRIS_Cols/2 - 1,Y:1,Color:2)
    
    ],
    // 第三种可能出现的组合 田
    [
    Block(X:TETRIS_Cols/2 - 1,Y:0,Color:3),
    Block(X:TETRIS_Cols/2,Y:0,Color:3),
    Block(X:TETRIS_Cols/2 - 1,Y:1,Color:3),
    Block(X:TETRIS_Cols/2 ,Y:1,Color:3)
    
    ],
    // 第四种可能出现的组合 L
    [
    Block(X:TETRIS_Cols/2 - 1,Y:0,Color:4),
    Block(X:TETRIS_Cols/2 - 1,Y:1,Color:4),
    Block(X:TETRIS_Cols/2 - 1,Y:2,Color:4),
    Block(X:TETRIS_Cols/2 ,Y:2,Color:4)
    
    ],
    // 第五种可能出现的组合 J
    [
    Block(X:TETRIS_Cols/2,Y:0,Color:5),
    Block(X:TETRIS_Cols/2,Y:1,Color:5),
    Block(X:TETRIS_Cols/2,Y:2,Color:5),
    Block(X:TETRIS_Cols/2 - 1,Y:2,Color:5)
    
    ],
    // 第六种可能出现的组合 ——
    [
    Block(X:TETRIS_Cols/2,Y:0,Color:6),
    Block(X:TETRIS_Cols/2,Y:1,Color:6),
    Block(X:TETRIS_Cols/2,Y:2,Color:6),
    Block(X:TETRIS_Cols/2,Y:3,Color:6)
    
    ],
    // 第七种可能出现的组合 土缺一
    [
    Block(X:TETRIS_Cols/2,Y:0,Color:7),
    Block(X:TETRIS_Cols/2-1,Y:1,Color:7),
    Block(X:TETRIS_Cols/2,Y:1,Color:7),
    Block(X:TETRIS_Cols/2 + 1,Y:1,Color:7)
    
    ],
    ]
    override var shouldAutorotate: Bool {
        return true
    }

    override var supportedInterfaceOrientations: UIInterfaceOrientationMask {
        if UIDevice.current.userInterfaceIdiom == .phone {
            return .allButUpsideDown
        } else {
            return .all
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Release any cached data, images, etc that aren't in use.
    }

    override var prefersStatusBarHidden: Bool {
        return true
    }
}
