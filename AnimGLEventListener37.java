package Texture;

import com.sun.opengl.util.j2d.TextRenderer;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.System.exit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.media.opengl.*;
import java.util.BitSet;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.glu.GLU;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Texture.AnimListener;
import Texture.TextureReader;
import java.io.FileNotFoundException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class AnimGLEventListener37 extends AnimListener implements MouseListener {

    int timer = 0;
    String time = java.time.LocalTime.now() + "";
    GLCanvas glc;
    boolean isleft = false;
    boolean istop = true;
    boolean isright = false;
    boolean isdown = false;
    boolean isleft1 = false;
    boolean istop1 = true;
    boolean isright1 = false;
    boolean isdown1 = false;
    boolean exit = false;
    boolean lo = false;
    public static boolean ispause = false;
    GLAutoDrawable gldddd;
    TextRenderer renderer = new TextRenderer(new Font("SanasSerif", Font.BOLD, 20));
    int xPosition = 90;
    int yPosition = 90;
    int animationIndex = 0;
    int maxWidth = 100;
    int maxHeight = 100;
    int x = 50, y = 5;
    int x1 = 3, y1 = 5;
    int score = 0;
    int aa = 65;
    int bb = 65;
    int cc = 65;
    int asd;
    int u;
    int drop;
    int drop2;
    int drop3;
    int r1 = 54, r2 = 54, r3 = 54, r4 = 54;
    int r11 = 54, r22 = 54, r33 = 54, r44 = 54, r55 = 54;
    int x11 = 65, x22 = 65, x33 = 65, x44 = 65, x55 = 65, x66 = 65;
    int score2 = 0;
    int score3 = 0;
    boolean Twolevel = false;
    int c, ozoz;
    boolean Threelevel = false;
    boolean drawEgg = true;
    FileInputStream music;
    AudioStream audios;
    boolean playingNow = false;
    boolean how = false;
    boolean player1 = false, Easy = false, Normal = false, Hard = false, sound = false,
            player2 = false, home = true, tank = false, tank1 = false, tank2 = false;
    String textureNames[] = {"hom.png", "levelone2p.png", "winlevel1.png",
        "EMH.png", "how.png", "level1.png", "level2.png", "level3.png",
        "ner5.png", "egg1.png", "pause.png", "level2.png", "winlevel2.png", "winlevel3.png",
        "loselevel1.png", "loswlevel2.png", "loselevel3.png","winplayerleft.png"};
    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];

    public void setway(boolean top, boolean down, boolean right, boolean left) {
        this.istop = top;
        this.isdown = down;
        this.isright = right;
        this.isleft = left;

    }

    public void setway1(boolean top, boolean down, boolean right, boolean left) {
        this.istop1 = top;
        this.isdown1 = down;
        this.isright1 = right;
        this.isleft1 = left;

    }

    public void setGLCanvas(GLCanvas glc) {
        this.glc = glc;
    }

    /*
     5 means gun in array pos
     x and y coordinate for gun 
     */
    public void init(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black
        try {
            music = new FileInputStream(new File("chicken dance song.wav"));
            audios = new AudioStream(music);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        AudioPlayer.player.start(audios);
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Imagedata
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        glc.repaint();
    }

    public void display(GLAutoDrawable gld) {

        gldddd = gld;
        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();
        if (home) {
            DrawBackgroundhom(gl, 0);
            try {
                if (playingNow) {
                    DrawTime();
                }
            } catch (ParseException ex) {
              System.err.println(ex.getMessage());
            }
        }
        if (ispause) {
            DrawPause(gl);
          
        }
        
        if(ozoz==3){
            DrawBackgroundhom(gl, 17);
           
            player2=false;
        }

        if (player2) {
            playingNow = true;

            DrawBackgrPlayer2(gl);
            handleKeyPress1();

            DrawSprite(gl, x, y, 8, 1);
            DrawSprite(gl, x1, y1, 8, 1);
            try {
                if (playingNow) {
                    DrawTime();
                }
            } catch (ParseException ex) {
                System.err.println(ex.getMessage());
            }
            int[] arr6 = {5, 21, 35};
            int[] arr61 = {54, 68, 83};
            if (x11 % 65 == 0 || x22 % 65 == 0 || x33 % 65 == 0) {
                u = (int) (Math.random() * (double) arr6.length);
            }
            if (x44 % 65 == 0 || x55 % 65 == 0 || x66 % 65 == 0) {
                c = (int) (Math.random() * (double) arr61.length);
            }
            DrawEgg(gl, arr6[u], x11);
            DrawEgg(gl, arr61[c], x44);

            collectEggs(x, y, arr6[u], x11);
            collectEggs2(x1, y1, arr6[u], x11);
            x11--;
            x22--;
            x33--;
            x44--;
            x55--;
            x66--;
            //System.out.println(aa);
            if (x11 == 3) {

                drawEgg = true;
                x11 = 65;
            }
            if (x22 == 3) {
                x22 = 65;
            }
            if (x33 == 3) {
                x33 = 65;

            }
            if (x44 == 3) {
                x44 = 65;

            }
            if (x55 == 3) {
                x55 = 65;

            }
            if (x66 == 3) {
                x66 = 65;

            }

        }
        if (player1) {
            playingNow = true;

            DrawEHM(gl);
            try {
                if (playingNow) {

                    DrawTime();
                }
            } catch (ParseException ex) {
                System.err.println(ex.getMessage());
            }
        }
        if (how) {
            DrawBackgrHowtoplay(gl);
            try {
                if (playingNow) {

                    DrawTime();
                }
            } catch (ParseException ex) {
                System.err.println(ex.getMessage());
            }
        }
        if (Twolevel) {
            DrawBackgroundhom(gl, 11);
            score = 4;
            DrawSprite(gl, x, y, 8, 1.5f);
            handleKeyPress();
            if (r1 == 54 && score != 8) {
                drop2++;

            }
            int[] arr1 = {13, 35, 56, 80};
            if (r1 % 54 == 0 || r2 % 54 == 0 || r3 % 54 == 0 || r4 % 54 == 0) {

                u = (int) (Math.random() * (double) arr1.length);
            }

            DrawEgg(gl, arr1[u], r1);

            collectEggs(x, y, arr1[u], r1);
            r1--;
            r2--;
            r3--;
            r4--;
            //System.out.println(aa);
            if (r1 == 3) {

                drawEgg = true;
                r1 = 54;
            }
            if (r2 == 3) {
                r2 = 54;
            }
            if (r3 == 3) {
                r3 = 54;
            }
            if (r4 == 3) {
                r4 = 54;
            }
        }
        if (Threelevel) {
            DrawBackgroundhom(gl, 7);
            score2 = 6;
            DrawSprite(gl, x, y, 8, 1.5f);
            handleKeyPress();
            if (r11 == 54 && score2 != 15) {
                drop3++;

            }
            int[] arr2 = {17, 32, 47, 61, 76};
            if (r11 % 54 == 0 || r22 % 54 == 0 || r33 % 54 == 0 || r44 % 54 == 0 || r55 % 54 == 0) {

                u = (int) (Math.random() * (double) arr2.length);
            }

            DrawEgg(gl, arr2[u], r11);

            collectEggs(x, y, arr2[u], r11);
            r11--;
            r22--;
            r33--;
            r44--;
            r55--;
            //System.out.println(aa);
            if (r11 == 3) {

                drawEgg = true;
                r11 = 54;
            }
            if (r22 == 3) {
                r22 = 54;
            }
            if (r33 == 3) {
                r33 = 54;
            }
            if (r44 == 3) {
                r44 = 54;
            }
            if (r55 == 3) {
                r55 = 54;
            }
        }
        if (Easy) {
            DrawEast(gl);
            DrawSprite(gl, x, y, 8, 1.5f);
            handleKeyPress();
            if (aa == 65 && score != 3) {
                drop += 3;

            }
            try {
                if (playingNow) {

                    DrawTime();
                }
            } catch (ParseException ex) {
                Logger.getLogger(AnimGLEventListener3.class.getName()).log(Level.SEVERE, null, ex);
            }
            int[] arr = {19, 45, 70};
            if (aa % 65 == 0 || bb % 65 == 0 || cc % 65 == 0) {

                u = (int) (Math.random() * (double) arr.length);
            }
            DrawEgg(gl, arr[u], aa);
            collectEggs(x, y, arr[u], aa);
            aa--;
            bb--;
            cc--;
            //System.out.println(aa);
            if (aa == 3) {

                drawEgg = true;
                aa = 65;
            }
            if (bb == 3) {
                bb = 65;
            }
            if (cc == 3) {
                cc = 65;

            }

        }

//        if(next){
//        
//        }
        if (Normal) {
            DrawEast(gl);
            DrawSprite(gl, x, y, 8, 1.5f);
            DrawEast(gl);
            DrawSprite(gl, x, y, 8, 1.5f);
            handleKeyPress();
            if (aa == 65 && score != 3) {
                drop += 3;

            }
            try {
                if (playingNow) {

                    DrawTime();
                }
            } catch (ParseException ex) {
                Logger.getLogger(AnimGLEventListener3.class.getName()).log(Level.SEVERE, null, ex);
            }
            int[] arr = {19, 45, 70};
            if (aa % 65 == 0 || bb % 65 == 0 || cc % 65 == 0) {

                u = (int) (Math.random() * (double) arr.length);
            }

            DrawEgg(gl, arr[u], aa);

            collectEggs(x, y, arr[u], aa);
            aa --;

            bb -- ;

            cc --;

            //System.out.println(aa);
            if (aa == 3) {

                drawEgg = true;
                aa = 65;
            }
            if (bb == 3) {
                bb = 65;
            }
            if (cc == 3) {
                cc = 65;

            }

        }
        if (Hard) {
            DrawEast(gl);
            DrawSprite(gl, x, y, 8, 1.5f);
            handleKeyPress();
//            if (aa == 65 && score != 3) {
//                drop += 3;
//            }
            if(aa==5){
                drop++;
                System.out.println(drop);
            }
            try {
                if (playingNow) {
                    DrawTime();
                }
            } catch (ParseException ex) {
                System.err.println(ex.getMessage());
            }
            int[] arr = {19, 45, 70};
            if (aa % 65 == 0 || bb % 65 == 0 || cc % 65 == 0) {
                u = (int) (Math.random() * (double) arr.length);
            }
            DrawEgg(gl, arr[u], aa);
            collectEggs(x, y, arr[u], aa);
            aa -= 2;
            bb -= 2;
            cc -= 2;
            //System.out.println(aa);
            if (aa == 3) {
                drawEgg = true;
                aa = 65;
            }
            if (bb == 3) {
                bb = 65;
            }
            if (cc == 3) {
                cc = 65;
            }
        }
        if (score == 3) {
            DrawBackgroundhom(gl, 2);
        }
        if (score2 == 8) {
            DrawBackgroundhom(gl, 12);
        }
        if (score3 == 15) {
            DrawBackgroundhom(gl, 13);
//            score3=16;
        }
//        if(score3==16){
//         System.exit(0);
//        }
        if (drop == 4) {
            DrawBackgroundhom(gl, 14);
//            Easy = false;
            Hard=false;
        }
//        if (drop2 == 7) {
//            DrawBackgroundhom(gl, 15);
//            Twolevel = false;
//        }
//        if (drop3 == 9) {
//            DrawBackgroundhom(gl, 16);
//            Threelevel = false;
//        }

        if (exit) {
            System.exit(0);
        }

//        someoneScored();
//        System.out.println(score+score1+score2);
//            onOrOffSound();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void DrawSprite(GL gl, int x, int y, int index, float scale) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (maxWidth / 2.0) - 0.9, y / (maxHeight / 2.0) - 0.9, 0);
        gl.glScaled(0.1 * scale, 0.1 * scale, 1);
        //System.out.println(x +" " + y);
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

    public void DrawBackgroundhom(GL gl, int index) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        //gl.glTranslated(-.90, .90, 0);
        //gl.glScaled(0.1, 0.1, 1);

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

    public void DrawBackgrPlayer2(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[1]);	// Turn Blending On

        gl.glPushMatrix();
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

    public void DrawEast(GL gl) {

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[5]);	// Turn Blending On

        gl.glPushMatrix();
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

    public void DrawBackgrHowtoplay(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[4]);	// Turn Blending On

        gl.glPushMatrix();
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

    public void DrawEHM(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[3]);	// Turn Blending On

        gl.glPushMatrix();
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

    public void DrawPause(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[10]);	// Turn Blending On

        gl.glPushMatrix();
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

    public void DrawHow(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[4]);	// Turn Blending On

        gl.glPushMatrix();
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

    public void DrawTime() throws ParseException {

        String time1 = time;
        String time2 = java.time.LocalTime.now() + "";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        long difference = date2.getTime() - date1.getTime();

        String fi = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(difference),
                TimeUnit.MILLISECONDS.toSeconds(difference)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(difference))
        );

        renderer.beginRendering(gldddd.getWidth(), gldddd.getHeight());
        renderer.draw(fi, 600, 620);
        renderer.endRendering();
    }

    public void DrawEgg(GL gl, int x, int y) {
        if (!drawEgg) {
            return;
        }
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[9]);	// Turn Blending On
        gl.glPushMatrix();
        gl.glTranslated(x / (maxWidth / 2.0) - 0.9, y / (maxHeight / 2.0) - 0.9, 0);
        gl.glScaled(0.1 * 0.3, 0.1 * 0.5, 1);
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
    ** Draw lead 
     */
 /*
   ** move the lead
     */
 /*
    ** set the lead
     */

 /*
     * KeyListener
     */
    public void handleKeyPress() {

        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (x > 5) {
                x--;
            }
        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (x < maxWidth - 15) {
                x++;
            }
        }

    }

    public void handleKeyPress1() {

        if (isKeyPressed(KeyEvent.VK_A)) {
            if (x1 > 1) {
                x1--;
            }
        }
        if (isKeyPressed(KeyEvent.VK_D)) {
            if (x1 < 40) {
                x1++;
            }
        }

        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (x > 53) {
                x--;
            }
        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (x < 90) {
                x++;
            }
        }

    }

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);

        if (isKeyPressed(KeyEvent.VK_P)) {
            if (!ispause) {
                ispause = !ispause;
                gldddd.repaint();
                Anim.animator.stop();

            } else {
                ispause = !ispause;
                gldddd.repaint();
                Anim.animator.start();
            }
        }
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

    @Override
    public void mouseClicked(MouseEvent e) {

        double x4 = e.getX();
        double y4 = e.getY();

//        System.out.println(x + " " + y);
        Component c = e.getComponent();
        double width = c.getWidth();
        double height = c.getHeight();
//        System.out.println(width + " " + height);
//get percent of GLCanvas instead of
//points and then converting it to our
//'100' based coordinate system.
        xPosition = (int) ((x4 / width) * 100);
        yPosition = ((int) ((y4 / height) * 100));
//reversing direction of y axis
        yPosition = 100 - yPosition;
        if (home) {
            if (xPosition <= 37 && xPosition >= 12 && yPosition <= 86 && yPosition >= 74) {
                player1 = true;
                home = false;
                //player1
            }
            if ((xPosition >= 12 && xPosition <= 36) && (yPosition >= 20 && yPosition <= 33)) {
                exit = true;
                //exit
            }
            if ((xPosition >= 13 && xPosition <= 37) && (yPosition >= 38 && yPosition <= 50)) {
                home = false;
                how = true;
                //how to play
            }

            if (xPosition <= 37 && xPosition >= 12 && yPosition <= 68 && yPosition >= 57) {
                player2 = true;
                home = false;
                //player 2
            }
            if (xPosition <= 95 && xPosition >= 90 && yPosition <= 95 && yPosition >= 86) {
                sound = !sound;
                onOrOffSound();
                home = true;
                //return of how
            }
        }

        if (player1) {
            if (xPosition <= 17 && xPosition >= 8 && yPosition <= 91 && yPosition >= 81) {
                player1 = false;
                home = true;
                time = java.time.LocalTime.now() + "";
                //return to homme
            }
            if (xPosition <= 65 && xPosition >= 33 && yPosition <= 80 && yPosition >= 63) {
                Easy = true;
                player1 = false;
                //easy
            }
            if (xPosition <= 65 && xPosition >= 33 && yPosition <= 55 && yPosition >= 39) {
                Normal = true;
                player1 = false;
                //mid
            }
            if (xPosition <= 65 && xPosition >= 33 && yPosition <= 33 && yPosition >= 17) {
                Hard = true;
                player1 = false;
                //hard
            }
        }

        if (how) {
            if (xPosition <= 89 && xPosition >= 81 && yPosition <= 21 && yPosition >= 11) {
                how = false;
                home = true;
                //return of how
            }
        }
        if (score == 3) {
            if (xPosition <= 93 && xPosition >= 75 && yPosition <= 21 && yPosition >= 12) {
                Easy = false;
                Twolevel = true;
               
                //return of how
            }
        }
        if (score == 3) {
            if (xPosition <= 93 && xPosition >= 75 && yPosition <= 21 && yPosition >= 12) {
                Normal = false;
                Twolevel = true;
            
                //return of how
            }
        }
        if (score == 5) {
            if (xPosition <= 93 && xPosition >= 75 && yPosition <= 21 && yPosition >= 12) {
                Hard = false;
                Twolevel = true;
               
                //return of how
            }
        }
        if (score3 == 8) {
            if (xPosition <= 93 && xPosition >= 75 && yPosition <= 21 && yPosition >= 12) {

                Twolevel = false;

                Threelevel = true;
               
                //return of how
            }
        }

        if (score3 == 15) {
            if (xPosition <= 93 && xPosition >= 75 && yPosition <= 21 && yPosition >= 12) {

                System.exit(0);
                
               
                //return of how
            }
        }
          if (drop == 4) {
            if (xPosition <= 93 && xPosition >= 75 && yPosition <= 21 && yPosition >= 12) {

                System.exit(0);
                
               
                //return of how
            }
        }
          
       if (ozoz == 3) {
            if (xPosition <= 93 && xPosition >= 75 && yPosition <= 21 && yPosition >= 12) {

                System.exit(0);
                
               
                //return of how
            }
        }

        System.out.println(xPosition + " " + yPosition);
        glc.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void onOrOffSound() {
        try {

            System.out.println(sound);
            if (sound) {
                System.out.println("here");
                AudioPlayer.player.stop(audios);

            } else {
                AudioPlayer.player.start(audios);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void collectEggs(int netX, int netY, int eggX, int eggY) {

        if (eggX <= netX + 7.5 && eggX >= netX - 7.5 && eggY == netY + 12) {
            score++;
            System.out.println("collect " + score);
            score2++;
            System.out.println("collect2 " + score2);
            score3++;
            System.out.println("collect3 " + score3);
            drawEgg = false;

        }

    }

    private void collectEggs2(int netX, int netY, int eggX, int eggY) {

        if (eggX <= netX + 7.5 && eggX >= netX - 7.5 && eggY == netY + 12) {
            ozoz++;
            System.out.println("this is the second" + ozoz);
            drawEgg = false;

        }

    }
}
