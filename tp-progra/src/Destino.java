public class Destino {
    private String destino;
    private int kms;

    public Destino(String destino, int kms) {
        this.destino = destino;
        this.kms = kms;
    }

    public String getDestino() {
        return this.destino;
    }

    public double getKms() {
        return this.kms;
    }

    @Override
    public boolean equals(Object d) {
        if (getClass() != d.getClass()) {
            return false;
        }
        Destino destino = (Destino) d;

        if (!this.destino.equals(destino.destino)) {
            return false;
        }

        if (this.kms != destino.kms) {
            return false;
        }

        return true;
    }
}


// destino no puede ser null && kms > 0
