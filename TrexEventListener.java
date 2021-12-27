package Texture;
 
 
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.BitSet;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import javax.media.opengl.glu.GLU;


   

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package project;

import java.awt.event.*;
import java.io.IOException;
import javax.media.opengl.*;

import java.util.BitSet;
import javax.media.opengl.glu.GLU;

public class TrexEventListener extends Texture.AnimListener {
        
    int animationIndex = 0;
    int animationIndex1=3;
    final float maxWidth = 1000;
    final float maxHeight = 650;
    final float borderSize = 100;
    float speed = 25;
    float x, y = -110;
    float x1,y1=90;
    float scrollBackground;
    static boolean  isMovingRight = true;
    boolean birdcheck=true;
    
    String textureNames[] 
            = { "idle.png",
                "walk1.png",
                "walk2.png",
                 "bird1.png",
                 "bird2.png",
                   "cocus1c.png",
                  "cocus3s.png", "cloud.png", 
                "land.png"};
    Texture.TextureReader.Texture textures[] = new Texture.TextureReader.Texture[textureNames.length];
    int textureIndecies[] = new int[textureNames.length];

    /*
     5 means gun in array pos
     x and y coordinate for gun 
     */
    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black
                
        
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        
        // We need to generate some numbers to associate textures with
        // and place it into textures array
        gl.glGenTextures(textureNames.length, textureIndecies, 0);
        
        for(int i = 0; i < textureNames.length; i++){
            try {
                textures[i] = Texture.TextureReader.readTexture(assetsFolderName + "\\" + textureNames[i] , true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                    GL.GL_TEXTURE_2D,
                    GL.GL_RGBA, // Internal Texel Format,
                    textures[i].getWidth(), textures[i].getHeight(),
                    GL.GL_RGBA, // External format from image,
                    GL.GL_UNSIGNED_BYTE,
                    textures[i].getPixels() // Imagedata
                    );
            } catch( IOException e ) {
              System.out.println(e);
              e.printStackTrace();
            }
        }
        
        gl.glLoadIdentity(); 
        gl.glOrtho(-maxWidth/2,  maxWidth/2,-maxHeight/2, maxHeight/2,-1,1);
    }
    
    public void display(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        
        
        System.out.println(scrollBackground);
        
        if(scrollBackground > 3*maxWidth/2)
            scrollBackground-=4*maxWidth/2;
        
        if(scrollBackground < -3*maxWidth/2)
            scrollBackground+=4*maxWidth/2;
        
        DrawBackground(gl, scrollBackground);
        
        handleKeyPress();
        animationIndex = animationIndex % 3;
        
        
//        DrawGraph(gl);
        DrawSprite(gl, -110, y, animationIndex);
        
         handleKeyPress1();
         if (birdcheck==true){
           animationIndex1 = 4;
           birdcheck=false;
         }else if(birdcheck==false) {animationIndex1=3;
            birdcheck=true;
         }
        Drawbird(gl, scrollBackground,y1, animationIndex1);
        
        
        //coco 
        
        DrawSprite(gl,scrollBackground+250,-110,5);
        DrawSprite(gl,scrollBackground+700,-110,6);
        
        // cloud 
           DrawSprite(gl,scrollBackground+200,100,7);
    }
   public void  Drawbird(GL gl, float x,float y,int index){

           gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[index]);	// Turn Blending On

        gl.glPushMatrix();
            gl.glTranslated(x, y1, 0);
             //gl.glScalef(isMovingRight? 1 : -1, 2, 1);
            gl.glScalef(maxWidth/10, maxHeight/10, 1);
            gl.glBegin(GL.GL_QUADS);
            // Front Face
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        gl.glDisable(GL.GL_BLEND);
   
   }
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    public void  Drawcactus(GL gl, float x,float y,int index){

           gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[5]);	// Turn Blending On

        gl.glPushMatrix();
            gl.glTranslated(x, -110, 0);
             //gl.glScalef(isMovingRight? 1 : -1, 2, 1);
            gl.glScalef(maxWidth/10, maxHeight/10, 1);
            gl.glBegin(GL.GL_QUADS);
            // Front Face
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        gl.glDisable(GL.GL_BLEND);
    
    }
    
    public void DrawSprite(GL gl,float x, float y, int index){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[index]);	// Turn Blending On
    
        gl.glPushMatrix();
            gl.glTranslatef(x, y, 0);
//            gl.glScalef(isMovingRight? 1 : -1, 2, 1);
            //System.out.println(x +" " + y);
            gl.glBegin(GL.GL_QUADS);
            // Front Face
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-50.0f, -50.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(50.0f, -50.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(50.0f, 50.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-50.0f, 50.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        gl.glDisable(GL.GL_BLEND);
    }
    
    public void DrawBackground(GL gl, float x){
        gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textureIndecies[textureIndecies.length - 1]);	// Turn Blending On

        gl.glPushMatrix();
            gl.glTranslated(x, 0, 0);
            gl.glScalef(4*maxWidth/2, maxHeight/2, 1);
            gl.glBegin(GL.GL_QUADS);
            // Front Face
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        gl.glDisable(GL.GL_BLEND);
    }
    
    /*
     * KeyListener
     */    
public void handleKeyPress1() {

        if (true) {
            if(!isMovingRight){
                if (x1 > -maxWidth/2+borderSize) {
                    x1-=speed;
                }
                else{
                    scrollBackground += speed;
                }
            }
//            isMovingRight = false;
            animationIndex1++;
        }
    
        }
    
    public void handleKeyPress() {


        if (true) {
            if(isMovingRight){
                if (x < maxWidth/2-borderSize) {
                    x+=speed;
                }
                else{
                    scrollBackground -= speed;
                }
            }
            isMovingRight = true;
            animationIndex++;
        }
    }

    public BitSet keyBits = new BitSet(256);
 
    @Override 
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
    } 
 
    @Override 
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    } 
 
    @Override 
    public void keyTyped(final KeyEvent event) {
        // don't care 
    } 
 
    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
}


