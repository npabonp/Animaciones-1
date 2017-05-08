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
import java.awt.Image;
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
    private int delay = 50; //La cantidad de milisegundos en las que cambia de pos.
    private Color color;
    private int secuencia;

    public NewPanel() {
        this.color = Color.BLACK;
        this.addMouseListener(this); // Recibe las acciones del mouse
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image fondo = loadImage("fondo.png"); //Cargar el fondo
        g.drawImage(fondo, 0, 0, null);
        /*Image gato = loadImage("cats.gif"); //Cargar el gato
        //Imagen, destinox,destinoy,ancho im,alto img, coord Inicial,0,coordenada fin,80,this
        g.drawImage(gato, x, 350, x + 132, 350 + 80, (this.secuencia * 132), 0, (this.secuencia * 132) + 132, 80, this);

        //Dibujar el carro 
        /*
        g.setColor(color);
        g.fillRect(x + 10, 350, 120, 70);//(x,y,ancho,alto)
        g.fillOval(x + 25, 420, 30, 30);
        g.fillOval(x + 75, 420, 30, 30);
        g.fillRect(x + 100, 360, 90, 25);
         
        //Dibujar los objetos
        g.setColor(Color.ORANGE); //Para cambiar el color de algo
        g.fillRect(100, x + 30, 30, 50);
        g.fillRect(210, x + 30, 30, 50);
        g.fillOval(300, x + 30, 25, 25);
         */

        //añcho dividido entre los muñecos que hay, multipl por la fila
        //(384/12)*7 =32
        //(256/8)*7 = 224
        //32*pos= 32*6 =192
        Image muñeco = loadImage("free_radical_game_sprites.png");//Cargar un muñeco
        g.drawImage(muñeco, x, 380, x + 40, 380 + 40, 
                (this.secuencia * 32) + 192, 192, (this.secuencia * 32) + 192 + 32, 192 + 32, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += 5; //Cantidad de pixeles que se mueve, dentro de mas pix mas rapido
        if (this.secuencia == 2) {
            this.secuencia = 0;
        } else {
            this.secuencia++;
        }
        //checkCollisions(); //antes del repaint, verificar si hay o no colisiones
        repaint();
    }
//para calcular la coordenada de un muñequito

    public Rectangle getBounds() { // es el borde que me contiene la figura
        Rectangle[] borde = new Rectangle[2];
        borde[0] = new Rectangle(x, 350, 120, x + 70);
        borde[1] = new Rectangle(x + 100, 360, 90, 25);
        return (borde[0]);

    }

    public Rectangle getBoundsO() {
        return new Rectangle(300, x + 20, 25, 25);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mp = e.getPoint();
        if (getBounds().contains(mp)) {
            this.timer.stop();
        }
    }

    public void checkCollisions() { //metodo para detectar las colisiones
        Rectangle tanque = getBounds();
        Rectangle objeto = getBoundsO();

        if (tanque.intersects(objeto))/*||tanque.intersects(objeto1)||tanque.intersects(objeto2))*/ {
            timer.stop();
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

    /*PARA PONER UNA IMAGEN EN NUESTRO ARCHIVO
    Copiar normal donde sea que esté, ir a files en netbeans y 
    pegarlo en la carpeta donde estemos trabajando    
     */
    public Image loadImage(String imageName) { //Cargar una imagen
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }

}
