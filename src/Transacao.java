public class Transacao {
    private static int ultID=0;
    private int id;
    
    public Transacao() {
        this.id=ultID++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
   
    
}
