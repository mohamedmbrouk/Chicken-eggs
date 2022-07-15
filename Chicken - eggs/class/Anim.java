package Texture;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package project;

import com.sun.opengl.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.BitSet;
import javax.media.opengl.*;
import javax.swing.*;
import Texture.AnimListener;

public class Anim extends JFrame {
    static GLCanvas glcanvas = null;
    public static Animator animator;
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Anim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
           Anim a = new Anim();
    }


    public Anim() {
        GLCanvas glcanvas;
        AnimGLEventListener37 listener = new AnimGLEventListener37();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(10);
        animator.add(glcanvas);
        animator.start();
//        MouseDisplay md = new MouseDisplay();
//only three JOGL lines of code ... and here they are
//        glcanvas = new GLCanvas();
//        glcanvas.addGLEventListener(listener);
        glcanvas.addMouseListener((MouseListener) listener);
        listener.setGLCanvas(glcanvas);

        setTitle("Anim Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }

   
}
