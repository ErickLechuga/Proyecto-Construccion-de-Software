/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecutar;

import Control.ControlOperacionesMatrices;
import Vista.VistaOperacionesMatrices;

/**
 *
 * @author erick
 */
public class EjecutarPrograma {
    public static void main(String[] args) {
        VistaOperacionesMatrices vista = new VistaOperacionesMatrices();
        ControlOperacionesMatrices control = new ControlOperacionesMatrices(vista);
        vista.setVisible(true);
    }
}
