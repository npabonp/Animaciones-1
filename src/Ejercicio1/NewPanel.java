/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;

//Libreria de menús, botones, etc
import java.awt.Color;
import javax.swing.*;
//Libreria de los componentes graficos
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//JPanel es un panel
//ActionListener es para mover
//MouseListener es para capturar todos los mov con el mouse
class NewPanel extends JPanel implements ActionListener, MouseListener {

    private int x;
    private Timer timer; //Simula que hace un clic
    private int delay = 25; //La cantidad de milisegundos en las que cambia de pos.
    private Color color;

    public NewPanel() {
        this.color = Color.BLUE;
        this.addMouseListener(this); // Recibe las acciones del mouse
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Dibujar el carro dos veces
        g.setColor(color);
        g.drawRect(x + 10, 200, 120, 70); //(x,y,ancho,alto)
        g.drawOval(x + 25, 270, 30, 30);
        g.drawOval(x + 75, 270, 30, 30);
        g.drawRect(x + 100, 210, 90, 25);

        //Dibujar los objetos
        g.setColor(Color.MAGENTA); //Para cambiar el color de algo
        g.drawRect(100, 80, 30, 50);
        g.drawRect(210, 80, 30, 50);
        g.drawOval(300, 80, 25, 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += 2; //Cantidad de pixeles que se mueve, dentro de mas pix mas rapido
        repaint();
    }

    public Rectangle getBounds() { // es el borde que me contiene la figura
        return new Rectangle(x, 200, 120, x+70);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mp = e.getPoint();
        if(getBounds().contains(mp)){
            this.timer.stop();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
