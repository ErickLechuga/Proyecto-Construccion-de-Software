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
 * Declaracion de la clase ControlOperacionesMatrices
 *
 *
 *
 */
public class ControlOperacionesMatrices implements ActionListener {

    //Definicion de los atributos privados

    private VistaOperacionesMatrices view;
    private int operacion = 0;
    private static int SUMA = 1;
    private static int MULT_ESCALAR = 2;
    private static int MULT_MATRI = 3;
    private static int INVERSA = 4;
    private static int DETER = 5;
    private static int GAUSS = 6;
    private static int CRAMER = 7;

    /**
     * Creacion del constructor
     *
     * @param viewOperaciones
     */
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

    /**
     * Metodo que utiliza el ActionListener para observar eventos de la vista
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //evento del boton suma de matrices
        if (view.getjButtonSuma() == e.getSource()) {
            deshabilitarOperaciones();
            view.getjButtonSuma().setBackground(new Color(255, 50, 50));
            operacion = SUMA;
        }
        
        
        
        
        
        

        //evento del boton Limpiar
        if (view.getjButtonLimpiar() == e.getSource()) {
            habilitarOperaciones();
            regresarColorOperaciones();
            limpiarMatrices();
        }
        //evento del boton Establecer
        if (view.getjButtonEstablecer() == e.getSource()) {
            int filas = Integer.parseInt(String.valueOf(view.getjComboBoxFilas().getSelectedItem()));
            int columnas = Integer.parseInt(String.valueOf(view.getjComboBoxColumnas().getSelectedItem()));

            if (operacion == MULT_MATRI) {
                if (filas != columnas) {
                    JOptionPane.showMessageDialog(null, "la fila de la matriz A debe coicidir con las columnas de la matriz B");
                } else {
                    establecerMatriz(filas, columnas);
                }
            } else {
                establecerMatriz(filas, columnas);
            }
        }
        //evento al momento de seleccionar el tamaño de las filas y columnas
        if (view.getjComboBoxColumnas() == e.getSource() || view.getjComboBoxColumnas() == e.getSource()) {
            if (view.getjComboBoxColumnas().getSelectedItem() != "--"
                    && view.getjComboBoxFilas().getSelectedItem() != "--") {
                view.getjButtonEstablecer().setEnabled(true);
            } else {
                view.getjButtonEstablecer().setEnabled(false);
            }
        }
    }

    /**
     * Meodo que construye las matrices despues de seleccionar el tamaño de
     * filas y columnas
     *
     * @param filas
     * @param columnas
     */
    public void establecerMatriz(int filas, int columnas) {
        DefaultTableModel modelMatriz1 = (DefaultTableModel) view.getjTableMatriz1().getModel();
        DefaultTableModel modelMatriz2 = (DefaultTableModel) view.getjTableMatriz2().getModel();
        modelMatriz1.setRowCount(filas);
        modelMatriz1.setColumnCount(columnas);
        //dependiendo de la operacion seleccionada se crea la segunda matriz
        if (operacion == MULT_ESCALAR) {
            //cuando es multiplicacion con un escalar
            modelMatriz2.setColumnCount(1);
            modelMatriz2.setRowCount(1);
        }
        if ((operacion == SUMA) || (operacion == MULT_MATRI)) {
            //cuando es suma o multiplicacion de matrices
            modelMatriz2.setColumnCount(filas);
            modelMatriz2.setRowCount(columnas);
        }
        if((operacion ==GAUSS) || (operacion==CRAMER)){
            modelMatriz2.setColumnCount(1);
            modelMatriz2.setRowCount(filas);
        }
    }

    /**
     * Metodo que bloquea los botones al seleccionar una de las operaciones de
     * matrices
     *
     */
    public void deshabilitarOperaciones() {
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

    public void habilitarOperaciones() {
        view.getjButtonDeterminante().setEnabled(true);
        view.getjButtonInversa().setEnabled(true);
        view.getjButtonLimpiar().setEnabled(false);
        view.getjButtonMultipEscalar().setEnabled(true);
        view.getjButtonMultiplicación().setEnabled(true);
        view.getjButtonResultado().setEnabled(false);
        view.getjButtonSolucionCramer().setEnabled(true);
        view.getjButtonSolucionGauss().setEnabled(true);
        view.getjButtonSuma().setEnabled(true);
        view.getjComboBoxColumnas().setEnabled(false);
        view.getjComboBoxFilas().setEnabled(false);
        view.getjButtonEstablecer().setEnabled(false);
    }

    public void regresarColorOperaciones() {
        view.getjButtonDeterminante().setBackground(Color.LIGHT_GRAY);
        view.getjButtonInversa().setBackground(Color.LIGHT_GRAY);
        view.getjButtonMultipEscalar().setBackground(Color.LIGHT_GRAY);;
        view.getjButtonMultiplicación().setBackground(Color.LIGHT_GRAY);;
        view.getjButtonSolucionCramer().setBackground(Color.LIGHT_GRAY);;
        view.getjButtonSolucionGauss().setBackground(Color.LIGHT_GRAY);;
        view.getjButtonSuma().setBackground(Color.LIGHT_GRAY);
    }

    public void limpiarMatrices() {
        DefaultTableModel modelMatriz1 = (DefaultTableModel) view.getjTableMatriz1().getModel();
        DefaultTableModel modelMatriz2 = (DefaultTableModel) view.getjTableMatriz2().getModel();
        modelMatriz1.setRowCount(0);
        modelMatriz1.setColumnCount(0);
        modelMatriz2.setRowCount(0);
        modelMatriz2.setColumnCount(0);
    }
}
