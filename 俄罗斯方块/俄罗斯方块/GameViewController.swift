//
//  GameViewController.swift
//  俄罗斯方块
//
//  Created by s20151104684 on 18/5/11.
//  Copyright © 2018年 s20151104684. All rights reserved.
//

import UIKit
import SpriteKit
import GameplayKit

class GameViewController: UIViewController {
    
    var scene:GameScene!

    override func viewDidLoad() {
        super.viewDidLoad()
       
        let skView = view as! SKView
        skView.isMultipleTouchEnabled=false
        
        scene=GameScene(size:skView.bounds.size)
        scene.scaleMode = .aspectFill
        
        skView.presentScene(scene)
        
        
      /*  if let view = self.view as! SKView? {
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
 */
    }
    
 /*
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
*/
    override var prefersStatusBarHidden: Bool {
        return true
    }
}
