/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Vista.VistaOperacionesMatrices;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author erick
 */
public class ControlOperacionesMatrices implements ActionListener {

    private VistaOperacionesMatrices view;
    private int operacion = 0;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getjButtonSuma() == e.getSource()) {
            EneableBottons();
            view.getjButtonSuma().setBackground(new Color(255, 50, 50));
        }
        
        
        
        
        
        
        if(view.getjButtonEstablecer() == e.getSource()){
            int filas = Integer.parseInt(String.valueOf(view.getjComboBoxFilas().getSelectedItem()));
            int columnas =Integer.parseInt(String.valueOf(view.getjComboBoxColumnas().getSelectedItem()));
            
            
            if(filas != columnas){
                JOptionPane.showMessageDialog(null,"la fila de la matriz A debe coicidir con las columnas de la matriz B");
            }else{
                establecerMatriz(filas, columnas);
            }
        }
        
        
        
        
        if(view.getjComboBoxColumnas()== e.getSource()|| view.getjComboBoxColumnas()== e.getSource()){
            if(view.getjComboBoxColumnas().getSelectedItem()!= "--" 
                    && view.getjComboBoxFilas().getSelectedItem()!= "--"){
                view.getjButtonEstablecer().setEnabled(true);
            }else{
                view.getjButtonEstablecer().setEnabled(false);
            }
        }
    }


public void establecerMatriz(int filas, int columnas){
        DefaultTableModel model = (DefaultTableModel) view.getjTableMatriz1().getModel();
        model.setRowCount(filas);
        model.setColumnCount(columnas);
    }
    
    public void EneableBottons(){
        view.getjButtonDeterminante().setEnabled(false);
        view.getjButtonInversa().setEnabled(false);
        view.getjButtonLimpiar().setEnabled(true);
        view.getjButtonMultipEscalar().setEnabled(false);
        view.getjButtonMultiplicación().setEnabled(false);
        view.getjButtonResultado().setEnabled(true);
        view.getjButtonSolucionCramer().setEnabled(false);
        view.getjButtonSolucionGauss().setEnabled(false);
        view.getjButtonSuma().setEnabled(false);
        view.getjComboBoxColumnas().setEnabled(true);
        view.getjComboBoxFilas().setEnabled(true);
    }
}
