package damas.presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import damas.dominio.Dapoobs;


public class JuegoGUI extends JFrame {

    private Dapoobs dapoobs;
    private boolean Click;
    private CasillaGUI Origen,Destino;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1,jSeparator2;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuItem Nuevo, Salir, Estadisticas;
    private TableroGUI tableroGUI;
    public JuegoGUI() {
        initComponents();
        this.Click = true;
        this.Origen = null;
        this.Destino = null;
        prepareElements();
        prepareActions();
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        super.pack();
    }
    private void prepareElements() {
        if(!Click) {
            Click = true;
            Origen.Prender();
        }
        dapoobs = new Dapoobs();
        actualizar();
    }
    private void prepareActions() {

        Nuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prepareElements();
            }
        });
        Estadisticas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {JOptionPane.showMessageDialog(null, dapoobs.toString());}
        });
        Salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }
    public void mover(CasillaGUI CasillaSelect) {
        if (Click) {
            if (CasillaSelect.posicionFicha()) {
                Origen = CasillaSelect;
                Origen.destacar();
                Click = false;
            }
            else {
              JOptionPane.showMessageDialog(this, "Haga clic en una parte valida.");
            }
        }
        else {
            Destino = CasillaSelect;
            dapoobs.moverDama(Origen.getPosicionX(), Origen.getPosicionY(), Destino.getPosicionX(), Destino.getPosicionY());
            Origen.Prender();
            Click = true;
            actualizar();
        }
        if (dapoobs.getJugadasincomer() == 40) {
            JOptionPane.showMessageDialog(this, "FIN DEL JUEGO! \n" + "40 ¡Jugar sin comer ninguna pieza!");
            prepareElements();
        }
        if (dapoobs.getGanador()==1) {
            JOptionPane.showMessageDialog(this, "FIN DEL JUEGO! \n" + dapoobs.getJugador1().getNombre() + " GANADOR!");
            prepareElements();
        }
        else if (dapoobs.getGanador()==2) {
            JOptionPane.showMessageDialog(this, "FIN DEL JUEGO! \n" + dapoobs.getJugador2().getNombre() + "GANADOR!");
            prepareElements();
        }
    }
    private void actualizar() {
        tableroGUI.actualizar(dapoobs);
    }

    private void initComponents() {
        //------------------//
        tableroGUI = new TableroGUI(this);
        jMenuBar1 = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        Nuevo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        Estadisticas = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        Salir = new javax.swing.JMenuItem();
        //------------------//
        menu.setText("Menu");
        Nuevo.setText("Nuevo");
        menu.add(Nuevo);
        menu.add(jSeparator1);
        Estadisticas.setText("Estadisticas");
        menu.add(Estadisticas);
        menu.add(jSeparator2);
        Salir.setText("Salir");
        menu.add(Salir);
        jMenuBar1.add(menu);
        setJMenuBar(jMenuBar1);
        //------------------//
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,false).addComponent(tableroGUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(tableroGUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pack();
        //------------------//
    }
}
