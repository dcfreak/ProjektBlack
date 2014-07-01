package de.bass.projektBlack.main;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import com.jme3.audio.AudioNode;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;


/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
  private AudioNode audio_gun;
  private AudioNode audio_nature;
  private Geometry player;
  
  

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        flyCam.setMoveSpeed(40);
        
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
        
         initKeys();
         initAudio();
    }
    
      private void initAudio() {
    /* gun shot sound is to be triggered by a mouse click. */
    audio_gun = new AudioNode(assetManager, "Sound/Effects/Gun.wav", false);
    audio_gun.setPositional(false);
    audio_gun.setLooping(false);
    audio_gun.setVolume(2);
    rootNode.attachChild(audio_gun);
 
    /* nature sound - keeps playing in a loop. */
    audio_nature = new AudioNode(assetManager, "Sound/Environment/Ocean Waves.ogg", true);
    audio_nature.setLooping(true);  // activate continuous playing
    audio_nature.setPositional(true);   
    audio_nature.setVolume(3);
    rootNode.attachChild(audio_nature);
    audio_nature.play(); // play continuously!
  }
      
        private void initKeys() {
    inputManager.addMapping("Shoot", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    inputManager.addListener(actionListener, "Shoot");
  }
        
        
     private ActionListener actionListener = new ActionListener() {
    @Override
    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("Shoot") && !keyPressed) {
        audio_gun.playInstance(); // play each instance once!
      }
    }
  };    
     
        
   @Override
  public void simpleUpdate(float tpf) {
    listener.setLocation(cam.getLocation());
    listener.setRotation(cam.getRotation());
  }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}