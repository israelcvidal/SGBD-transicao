import java.util.HashMap;
import java.util.Map;

public class Grafo {
    private final Map<String, Vertice> vertices;
    
    public Grafo() {
        vertices = new HashMap<>();
        vertices.put("TR_Iniciada", new Vertice("TR_Iniciada"));
        vertices.put("Ativa", new Vertice("Ativa"));
        vertices.put("Processo_Cancelamento", new Vertice("Processo_Cancelamento"));
        vertices.put("Processo_Efetivacao", new Vertice("Processo_Efetivacao"));
        vertices.put("Efetivada", new Vertice("Efetivada"));
        vertices.put("TR_Finalizada", new Vertice("TR_Finalizada"));
    }

    public Map<String, Vertice> getVertices() {
        return vertices;
    }
    
    public boolean tr_begin(Transacao tr) {
        vertices.get("TR_Iniciada").addTransacao(tr);
        return true;
    }
    
    private boolean rw(int id) {
    	Transacao tr = vertices.get("TR_Iniciada").getTransacao(id);
        if(tr != null) {
            vertices.get("Ativa").addTransacao(tr);
            vertices.get("TR_Iniciada").delTransacao(tr);
            return true;
        }
        return false;        
    }
        
    
    public boolean read(int id) {
        return rw(id);
    }
    
    public boolean write(int id) {
        return rw(id);
    }
    
    public boolean tr_terminate(int id) {
    	Transacao tr = vertices.get("Ativa").getTransacao(id);
        if( tr != null) {
            vertices.get("Processo_Efetivacao").addTransacao(tr);
            vertices.get("Ativa").delTransacao(tr);
            return true;
        }
        
        return false;
    }
    
    public boolean tr_rollback(int id) {
    	Transacao tr =  vertices.get("Ativa").getTransacao(id);
        if(tr != null) {
            vertices.get("Processo_Cancelamento").addTransacao(tr);
            vertices.get("Ativa").delTransacao(tr);
            return true;
        }
        
        tr = vertices.get("Processo_Efetivacao").getTransacao(id);
        if(tr != null) {
            vertices.get("Processo_Cancelamento").addTransacao(tr);
            vertices.get("Processo_Efetivacao").delTransacao(tr);
            return true;
        }
        
        return false;
    }
    
    public boolean tr_commit(int id) {
    	Transacao tr = vertices.get("Processo_Efetivacao").getTransacao(id);
        if(tr != null) {
            vertices.get("Efetivada").addTransacao(tr);
            vertices.get("Processo_Efetivacao").delTransacao(tr);
            return true;
        }
        return false;
    }
    
    public boolean tr_finish(int id) {
    	Transacao tr = vertices.get("Processo_Cancelamento").getTransacao(id);
        if(tr != null) {
            vertices.get("TR_Finalizada").addTransacao(tr);
            vertices.get("Processo_Cancelamento").delTransacao(tr);
            return true;
        }
        
        tr = vertices.get("Efetivada").getTransacao(id);
        if(tr != null) {
            vertices.get("TR_Finalizada").addTransacao(tr);
            vertices.get("Efetivada").delTransacao(tr);
            return true;
        }
        return false;
    }   
}
