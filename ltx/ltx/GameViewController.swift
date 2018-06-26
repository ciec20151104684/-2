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
