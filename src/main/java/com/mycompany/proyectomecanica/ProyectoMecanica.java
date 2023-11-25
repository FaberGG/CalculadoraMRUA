/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectomecanica;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author sofi_
 */
public class ProyectoMecanica {

    public static void main(String[] args) {

        // Instancia de la interfaz gráfica
        InterfazG objinterfaz = new InterfazG();
        // Crear una instancia de la clase Mrua
        Mrua objMrua = new Mrua();
        // ActionListener para el botón Calcular
        objinterfaz.agregarListenerBotonCalcular((ActionEvent e) -> {

            //DEFINO LAS VARIABLES
            Double a = objinterfaz.getJaceleracion();
            Double t = objinterfaz.getJtiempo();
            Double vo = objinterfaz.getJvelocidadinicial();
            Double vf = objinterfaz.getJvelocidadfinal();
            Double xo = objinterfaz.getJposicioninicial();
            Double xf = objinterfaz.getJposicionfinal();

            //se setean las variables
            objMrua.setA(a);
            objMrua.setT(t);
            objMrua.setVo(vo);
            objMrua.setVf(vf);
            objMrua.setXo(xo);
            objMrua.setXf(xf);

            // CALCULAR CON LAS VARIABLES INGRESADAS
            objMrua.usarFormula1();
            objMrua.usarFormula2();
            objMrua.usarFormula3();
            objMrua.usarFormula4();

            //activa el boton graficas solo si puede graficar
            if (objMrua.getVo() != null && objMrua.getT() != null && objMrua.getA() != null) {
                if (objMrua.getXo() != null) {
                    objinterfaz.Bgraficarxt.setEnabled(true);
                }
                objinterfaz.Bgraficarvt.setEnabled(true);
            } else {
                objinterfaz.Bgraficarxt.setEnabled(false);
                objinterfaz.Bgraficarvt.setEnabled(false);

            }

            // Actualizar la interfaz gráfica con los resultados si es necesario
            //Llamamos a los set para setear 
            objinterfaz.setjRAceleracion(objMrua.getA());
            objinterfaz.setjRTiempo(objMrua.getT());
            objinterfaz.setjRAVelocidadFinal(objMrua.getVf());
            objinterfaz.setjRVelocidadInicial(objMrua.getVo());
            objinterfaz.setjRPosicionInicial(objMrua.getXo());
            objinterfaz.setjRPosicionFinal(objMrua.getXf());
        });

        //action listener para los botones graficar
        objinterfaz.agregarListenerBgreficarxt((ActionEvent e) -> {

            //DEFINO LAS VARIABLES
            Double a = objMrua.getA();
            Double t = objMrua.getT();
            Double vo = objMrua.getVo();
            Double xo = objMrua.getXo();

            //LOGICA DEL BOTON
            // Crear una serie de datos para la gráfica
            XYSeries series = new XYSeries("Posición vs Tiempo");

            // Calcular la posición en diferentes puntos de tiempo y agregarlos a la serie
            for (double tActual = 0; tActual <= t; tActual += 0.01) {
                double posicion = xo + (vo * tActual) + ((a * (tActual * tActual)) / 2);
                series.add(tActual, posicion);
            }

            // Crear un conjunto de datos con la serie
            XYSeriesCollection dataset = new XYSeriesCollection(series);

            // Crear la gráfica
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Posición vs Tiempo",
                    "Tiempo",
                    "Posición",
                    dataset
            );

            // Crear el panel de la gráfica
            ChartPanel chartPanel = new ChartPanel(chart);

            // Crear un nuevo JFrame para mostrar la gráfica
            JFrame frame = new JFrame("Gráfica de Posición vs Tiempo");

            // Agregar el panel de la gráfica al JFrame
            frame.getContentPane().add(chartPanel);

            // Configurar el JFrame
            frame.pack();
            frame.setLocationRelativeTo(null); // Centrar en la pantalla
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });

        objinterfaz.agregarListenerBgreficarvt((ActionEvent e) -> {
            //DEFINO LAS VARIABLES
            Double a = objMrua.getA();
            Double t = objMrua.getT();
            Double vo = objMrua.getVo();

            //LOGICA DEL BOTON
            // Crear una serie de datos para la gráfica
            XYSeries series = new XYSeries("Velocidad vs Tiempo");

            // Calcular la posición en diferentes puntos de tiempo y agregarlos a la serie
            for (double tActual = 0; tActual <= t; tActual += 0.1) {
                double velocidad = vo + (a * tActual);
                series.add(tActual, velocidad);
            }

            // Crear un conjunto de datos con la serie
            XYSeriesCollection dataset = new XYSeriesCollection(series);

            // Crear la gráfica
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Velocidad vs Tiempo",
                    "Tiempo (s)",
                    "Velocidad (m/s)",
                    dataset
            );

            ChartPanel chartPanel = new ChartPanel(chart);
            JFrame frame = new JFrame("Gráfica de Velocidad vs Tiempo");
            frame.getContentPane().add(chartPanel);

            // Configurar el JFrame
            frame.pack();
            frame.setLocationRelativeTo(null); // Centrar en la pantalla
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });

        // Mostrar la interfaz gráfica
        objinterfaz.setVisible(true);
    }

}