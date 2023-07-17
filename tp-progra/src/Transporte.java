import java.util.ArrayList;

public class Transporte {
    private String matricula;
    private double cargaMax;
    private double capacidadMax;
    private boolean tieneRefrigeracion;
    protected double costoKm;
    protected double segCarga;

    private Destino destino;
    private boolean enViaje;

    private ArrayList<Paquete> paquetes;

    public Transporte(String matricula, double cargaMax, double capacidadMax, boolean tieneRefrigeracion,
                      double costoKm, double segCarga) {
        this.matricula = matricula;
        this.cargaMax = cargaMax;
        this.capacidadMax = capacidadMax;
        this.tieneRefrigeracion = tieneRefrigeracion;
        this.costoKm = costoKm;
        this.segCarga = segCarga;

        this.paquetes = new ArrayList<Paquete>();
    }

    public Transporte(String matricula, double cargaMax, double capacidadMax, double costoKm) {
        this.matricula = matricula;
        this.cargaMax = cargaMax;
        this.capacidadMax = capacidadMax;
        this.costoKm = costoKm;

        this.segCarga = 0;
        this.tieneRefrigeracion = false;

        this.paquetes = new ArrayList<Paquete>();
    }

    @Override
    public boolean equals(Object t) {
        System.out.println(getClass() + " " + t.getClass());
        if (getClass() != t.getClass()) {
            return false;
        }
        Transporte transporte = (Transporte) t;

        if (!transporte.getDestino().equals(this.destino)) {
            return false;
        }

        boolean estanTodosLosPaquetes = true;
        for (Paquete p : this.paquetes) {
            boolean elPaqueteEsta = false;
            for (Paquete p1 : transporte.getPaquetes()) {
                elPaqueteEsta = elPaqueteEsta || p.equals(p1);
            }
            estanTodosLosPaquetes = estanTodosLosPaquetes && elPaqueteEsta;
        }

        return estanTodosLosPaquetes;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public double calcularCostoDelViaje() {
        return this.costoKm * this.destino.getKms() + segCarga;
    }

    public boolean esRefrigerado() {
        return this.tieneRefrigeracion;
    }

    public Destino getDestino() {
        return this.destino;
    }

    public boolean estaEnViaje() {
        return this.enViaje;
    }

    public ArrayList<Paquete> getPaquetes() {
        return this.paquetes;
    }

    public void iniciarViaje() throws Exception {
        if (this.paquetes.size() < 1) {
            throw new RuntimeException("No hay paquetes cargados");
        }
        if (this.destino == null) {
            throw new RuntimeException("No hay destino asignado");
        }
        if (this.enViaje) {
            throw new RuntimeException("Ya esta en viaje");
        }

        this.enViaje = true;
    }

    public void finalizarViaje() throws Exception {
        if (!this.enViaje) {
            throw new RuntimeException("No esta en viaje");
        }
        this.descargarPaquetes();
        this.destino = null;
        this.enViaje = false;
    }

    public void asignarDestino(Destino destino) {
        this.destino = destino;
    }

    private double pesoTotal() {
        double pesoTotal = 0.0;
        for (Paquete paquete : this.paquetes) {
            pesoTotal += paquete.getPeso();
        }
        return pesoTotal;
    }

    public double getVolumenTotal() {
        double volumenTotal = 0.0;
        for (Paquete paquete : this.paquetes) {
            volumenTotal += paquete.getVolumen();
        }
        return volumenTotal;
    }

    public void cargarPaquete(Paquete p) throws Exception {
        if (this.pesoTotal() + p.getPeso() > this.cargaMax || this.getVolumenTotal() + p.getVolumen() > this.capacidadMax) {
            throw new Exception("El paquete no entra");
        }
        this.paquetes.add(p);
    }

    private void descargarPaquetes() {
        this.paquetes = new ArrayList<Paquete>();
    }
}

// matricula > 0 && cargaMax >= 0 && capacidadMax >= 0 && costoKm >= 0 && paquetes != null
