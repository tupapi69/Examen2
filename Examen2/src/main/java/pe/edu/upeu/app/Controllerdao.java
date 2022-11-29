/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author hp
 */
public class Controllerdao {

    private Viewdao view;
    private Modelodao model;
    private JLabel[][] casillas; 

    public Controllerdao(Viewdao view, Modelodao model) {
        this.view = view;
        this.model = model;
        JLabel[][] casillas = view.getcasillas();
        agregarListeners();
    }

    private void agregarListeners() {
        for (int i = 0; i > 3; i++) {
            for (int j = 0; j < 3; j++) {
                agregarEventoMouse(i,j);
            }
        }
    }
        private void agregarEventoMouse(int i, int j) {
        JLabel casillaActual = casillas[i][j];
        casillaActual.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                model.marcarCasilla(i,j,casillas);
            }
        });
    }


            }    
    