import java.util.HashMap;
import java.util.Map;

public class Vertice {
    private String estado;
    private Map<Integer, Transacao> transacoes;
    
    public Vertice(String estado) {
        this.estado = estado;
        this.transacoes = new HashMap<>();
    }

    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Map<Integer, Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(Map<Integer, Transacao> transacoes) {
        this.transacoes = transacoes;
    }
    
    public void addTransacao(Transacao tr) {
        this.transacoes.put(tr.getId(), tr);
    }
    
    public void delTransacao(Transacao tr) {
        this.transacoes.remove(tr.getId());
    }
    
    public Transacao getTransacao(int id) {
    	return transacoes.get(id);
    	
    }

    
}
