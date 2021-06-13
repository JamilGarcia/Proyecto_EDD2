package proyecto_edd2;

public class Llave {

    long offset;
    int llave;

    public Llave(long offset, int llave) {
        this.offset = offset;
        this.llave = llave;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getLlave() {
        return llave;
    }

    public void setLlave(int llave) {
        this.llave = llave;
    }
    
    
}
