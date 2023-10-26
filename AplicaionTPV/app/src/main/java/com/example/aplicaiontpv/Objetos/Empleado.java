package com.example.aplicaiontpv.Objetos;

/**
 * Clase que representa a un empleado con atributos como DNI, puesto, nombre, apellidos, antigüedad, número de seguridad social y contraseña.
 */
public class Empleado {
    private int dni;
    private String puesto;
    private String nombre;
    private String apellidos;
    private int antiguedad;
    private int nSeguridadSocial;
    private String paswd;

    /**
     * Constructor que inicializa un objeto Empleado con los atributos proporcionados.
     *
     * @param dni               El número de DNI del empleado.
     * @param puesto            El puesto o cargo del empleado.
     * @param nombre            El nombre del empleado.
     * @param apellidos         Los apellidos del empleado.
     * @param antiguedad        La antigüedad del empleado en años.
     * @param nSeguridadSocial  El número de seguridad social del empleado.
     * @param paswd             La contraseña del empleado.
     */
    public Empleado(int dni, String puesto, String nombre, String apellidos, int antiguedad, int nSeguridadSocial, String paswd) {
        this.dni = dni;
        this.puesto = puesto;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.antiguedad = antiguedad;
        this.nSeguridadSocial = nSeguridadSocial;
        this.paswd = paswd;
    }

    /**
     * Constructor sin argumentos que crea un objeto Empleado vacío.
     */
    public Empleado() {
    }

    /**
     * Obtiene el número de DNI del empleado.
     *
     * @return El número de DNI del empleado.
     */
    public int getDni() {
        return dni;
    }

    /**
     * Establece el número de DNI del empleado.
     *
     * @param dni El número de DNI a establecer.
     */
    public void setDni(int dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el puesto o cargo del empleado.
     *
     * @return El puesto o cargo del empleado.
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * Establece el puesto o cargo del empleado.
     *
     * @param puesto El puesto o cargo a establecer.
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * Obtiene el nombre del empleado.
     *
     * @return El nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del empleado.
     *
     * @param nombre El nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del empleado.
     *
     * @return Los apellidos del empleado.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del empleado.
     *
     * @param apellidos Los apellidos a establecer.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene la antigüedad del empleado en años.
     *
     * @return La antigüedad del empleado en años.
     */
    public int getAntiguedad() {
        return antiguedad;
    }

    /**
     * Establece la antigüedad del empleado en años.
     *
     * @param antiguedad La antigüedad a establecer.
     */
    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    /**
     * Obtiene el número de seguridad social del empleado.
     *
     * @return El número de seguridad social del empleado.
     */
    public int getnSeguridadSocial() {
        return nSeguridadSocial;
    }

    /**
     * Establece el número de seguridad social del empleado.
     *
     * @param nSeguridadSocial El número de seguridad social a establecer.
     */
    public void setnSeguridadSocial(int nSeguridadSocial) {
        this.nSeguridadSocial = nSeguridadSocial;
    }

    /**
     * Obtiene la contraseña del empleado.
     *
     * @return La contraseña del empleado.
     */
    public String getPaswd() {
        return paswd;
    }

    /**
     * Establece la contraseña del empleado.
     *
     * @param paswd La contraseña a establecer.
     */
    public void setPaswd(String paswd) {
        this.paswd = paswd;
    }
}