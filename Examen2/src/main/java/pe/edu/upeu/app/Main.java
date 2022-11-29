/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app;

/**
 *
 * @author hp
 */
public class Main {
    public static void main(String args[]) {
        Modelodao model = new Modelodao();
        Viewdao view = new Viewdao();
        Controllerdao controlador = new Controllerdao(view, model  );
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }
}
