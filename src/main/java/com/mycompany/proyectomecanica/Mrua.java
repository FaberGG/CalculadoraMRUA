package com.mycompany.proyectomecanica;

import java.util.Arrays;

public class Mrua {
    //atributos, los tipo de dato deben ser Double para usar null

    private Double a, t, vo, vf, xo, xf;
    private double p;

    //constructor
    public Mrua() {

    }

//formula para vf, vo, t, a (solo una incognita)
    public void usarFormula1() {
//verifica cuantas variables se ingresaron
        if (vo == null && vf == null) {
            vo = 0.0;
        }
        long datosNoNull = Arrays.asList(a, t, vo, vf).stream().filter(v -> v != null).count();

//comprueba que hay suficientes datos para usar esta formula ._.
        if (datosNoNull >= 3) {
            //condicionales
            if (a == null && t != 0.0) {
                a = (vf - vo) / t;
            } else if (t == null && a != 0.0) {
                t = (vf - vo) / a;
            } else if (vo == null) {
                vo = vf - (a * t);
            } else if (vf == null) {
                vf = vo + (a * t);
            }
        }
    }

//formula para t, vo, vf, xo, xf (solo una incognita xo=0 si xo==null)
    public void usarFormula2() {
        if (vo == null && vf == null) {
            vo = 0.0;
        }
        long datosNoNull = Arrays.asList(t, vo, vf, xo, xf).stream().filter(v -> v != null).count();

        if (datosNoNull >= 3) {
            if (xo == null && datosNoNull == 4) {
                //calcula la velocidad inicial con al menos 4 variables
                xo = xf - (((vf - vo) / 2) * t);
            } else if (xo == null && datosNoNull == 3) {
                xo = 0.0;
            }
            //condicionales formulas
            if (t == null) {
                t = (2 * (xf - xo)) / (vo + vf);
            } else if (xf == null) {
                xf = ((vf - vo) / 2) * t + xo;
            } else if (vo == null) {
                xo = vf - (2 * (xf - xo)) / t;//
            } else if (vf == null) {
                vf = 2 * (xf - xo) + (vo * t);
            }
        }
    }

//formula para a, vo, vf, xo, xf (solo una incognita xo=0 si xo==null)
    public void usarFormula3() {
        if (vo == null && vf == null) {
            vo = 0.0;
        }
        long datosNoNull = Arrays.asList(a, vo, vf, xo, xf).stream().filter(v -> v != null).count();

        if (datosNoNull >= 3) {
            if (xo == null && datosNoNull == 4) {
                //calcula la velocidad inicial con al menos 4 variables
                xo = xf - (((vf * vf) - (vo * vo)) / (2 * a));
            } else if (xo == null && datosNoNull == 3) {
                xo = 0.0;
            }

            //CONDICIONALES FORMULAS:
            if (a == null) {
                //calcular aceleracion
                a = ((vf * vf) - (vo * vo)) / (2 * (xf - xo));
            } else if (vo == null) {
                //calcular velocidad inicial
                double radicando = (vf * vf) - (2 * a * (xf - xo));
                vo = Double.valueOf(Math.sqrt(radicando));
            } else if (vf == null) {
                //calcular velocidad final
                double radicando = (vf * vf) + (2 * a * (xf - xo));
                vf = Double.valueOf(Math.sqrt(radicando));
            } else if (xf == null) {
                //calcular pocicion final
                xf = xo + (((vf * vf) - (vo * vo)) / (2 * a));
            }
        }
    }

//formula para a, t, vo, xo, xf (solo una incognita xo=0 si xo==null)
    public void usarFormula4() {
        //formula para a, vo, vf, xo, xf (solo una incognita xo=0 si xo==null)
        if (vo == null && vf == null) {
            vo = 0.0;
        }
        long datosNoNull = Arrays.asList(a, t, vo, xo, xf).stream().filter(v -> v != null).count();

        if (datosNoNull >= 3) {

            if (xo == null && datosNoNull == 4) {
                //calcula la velocidad inicial con al menos 4 variables
                xo = xf - (vo * t) - ((a / 2) * (t * t));
            } else if (xo == null && datosNoNull == 3) {
                xo = 0.0;
            }

            //CONDICIONALES FORMULAS:
            if (a == null && t != 0.0) {
                //calcular aceleracion
                a = (2 * ((xf - xo) - (vo * t))) / (t * t);
            } else if (t == null) {
                // calcular tiempo
                double radicando = (vo * vo) + (2 * a * (xf - xo));
                if (radicando >= 0) {
                    Double root1 = (-vo + Math.sqrt(radicando)) / 2;
                    Double root2 = (-vo - Math.sqrt(radicando)) / 2;

                    if (root1 >= 0.0) {
                        t = root1;
                    } else if (root2 >= 0.0) {
                        t = root2;
                    }
                }
            } else if (vo == null) {
                vo = (2 * (xf - xo) - (a * (t * t))) / (2 * t);
            } else if (xf == null) {
                xf = xo + (vo * t) + ((a * (t * t)) / 2);
            }
        }
    }
//setters y getters

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getT() {
        return t;
    }

    public void setT(Double t) {
        this.t = t;
    }

    public Double getVo() {
        return vo;
    }

    public void setVo(Double vo) {
        this.vo = vo;
    }

    public Double getVf() {
        return vf;
    }

    public void setVf(Double vf) {
        this.vf = vf;
    }

    public Double getXo() {
        return xo;
    }

    public void setXo(Double xo) {
        this.xo = xo;
    }

    public Double getXf() {
        return xf;
    }

    public void setXf(Double xf) {
        this.xf = xf;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

}
