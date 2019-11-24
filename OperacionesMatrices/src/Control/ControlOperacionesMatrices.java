/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Vista.VistaOperacionesMatrices;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author erick
 */
public class ControlOperacionesMatrices implements ActionListener {

    private VistaOperacionesMatrices view;

    public ControlOperacionesMatrices(VistaOperacionesMatrices viewOperaciones) {
        this.view = viewOperaciones;
        this.view.getjButtonDeterminante().addActionListener(this);
        this.view.getjButtonEstablecer().addActionListener(this);
        this.view.getjButtonInversa().addActionListener(this);
        this.view.getjButtonLimpiar().addActionListener(this);
        this.view.getjButtonMultipEscalar().addActionListener(this);
        this.view.getjButtonMultiplicación().addActionListener(this);
        this.view.getjButtonResultado().addActionListener(this);
        this.view.getjButtonSolucionCramer().addActionListener(this);
        this.view.getjButtonSolucionGauss().addActionListener(this);
        this.view.getjButtonSuma().addActionListener(this);
        this.view.getjComboBoxColumnas().addActionListener(this);
        this.view.getjComboBoxFilas().addActionListener(this);
        this.view.getjPanelMatriz1().addAncestorListener(null);
        this.view.getjPanelMatriz2().addAncestorListener(null);
        this.view.getjPanelResultado().addAncestorListener(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    public void inicializarMatriz(){
       
    }
}
