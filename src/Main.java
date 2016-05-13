import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static Transacao criar_transacao(Grafo grafo){
		Transacao tr = new Transacao();
		grafo.tr_begin(tr);
		return tr;
	}
	
	public static void mostrar_iniciada(Grafo grafo){
		Map<Integer, Transacao> transacoes;
		Vertice  tr_iniciada;
		Set<Integer> idTransacoes;
		java.util.Iterator<Integer> it;
		
		tr_iniciada = grafo.getVertices().get("TR_Iniciada");
		
		transacoes = tr_iniciada.getTransacoes();
		idTransacoes = transacoes.keySet();
		it = idTransacoes.iterator();

		System.out.println("id das transacoes INICIADAS:");
		while(it.hasNext()){
			System.out.print(transacoes.get(it.next()).getId() +  " ");	
		}
		System.out.println();
		
	}
	
	public static void mostrar_ativa(Grafo grafo){
		Map<Integer, Transacao> transacoes;
		Vertice  ativa;
		Set<Integer> idTransacoes;
		java.util.Iterator<Integer> it;
		
		ativa = grafo.getVertices().get("Ativa");
		
		transacoes = ativa.getTransacoes();
		idTransacoes = transacoes.keySet();
		it = idTransacoes.iterator();

		System.out.println("id das transacoes ATIVAS:");
		while(it.hasNext()){
			System.out.print(transacoes.get(it.next()).getId() +  " ");	
		}
		System.out.println();
		
	}
	
	public static void mostrar_cancelamento(Grafo grafo){
		Map<Integer, Transacao> transacoes;
		Vertice  processo_cancelamento;
		Set<Integer> idTransacoes;
		java.util.Iterator<Integer> it;
		
		processo_cancelamento = grafo.getVertices().get("Processo_Cancelamento");
		
		transacoes = processo_cancelamento.getTransacoes();
		idTransacoes = transacoes.keySet();
		it = idTransacoes.iterator();

		System.out.println("id das transacoes EM PROCESSO DE CANCELAMENTO:");
		while(it.hasNext()){
			System.out.print(transacoes.get(it.next()).getId() +  " ");	
		}
		System.out.println();
		
	}
	
	public static void mostrar_efetivacao(Grafo grafo){
		Map<Integer, Transacao> transacoes;
		Vertice  processo_efetivacao;
		Set<Integer> idTransacoes;
		java.util.Iterator<Integer> it;
		
		processo_efetivacao = grafo.getVertices().get("Processo_Efetivacao");
		
		transacoes = processo_efetivacao.getTransacoes();
		idTransacoes = transacoes.keySet();
		it = idTransacoes.iterator();

		System.out.println("id das transacoes EM PROCESSO DE EFETIVACAO:");
		while(it.hasNext()){
			System.out.print(transacoes.get(it.next()).getId() +  " ");	
		}
		System.out.println();
	
	}
	
	public static void mostrar_efetivada(Grafo grafo){
		Map<Integer, Transacao> transacoes;
		Vertice  efetivada;
		Set<Integer> idTransacoes;
		java.util.Iterator<Integer> it;
		
		efetivada = grafo.getVertices().get("Efetivada");
		
		transacoes = efetivada.getTransacoes();
		idTransacoes = transacoes.keySet();
		it = idTransacoes.iterator();

		System.out.println("id das transacoes EFETIVADAS:");
		while(it.hasNext()){
			System.out.print(transacoes.get(it.next()).getId() +  " ");	
		}
		System.out.println();
		
	}
	
	public static void mostrar_finalizada(Grafo grafo){
		Map<Integer, Transacao> transacoes;
		Vertice  tr_finalizada;
		Set<Integer> idTransacoes;
		java.util.Iterator<Integer> it;
		
		tr_finalizada = grafo.getVertices().get("TR_Finalizada");
		
		transacoes = tr_finalizada.getTransacoes();
		idTransacoes = transacoes.keySet();
		it = idTransacoes.iterator();

		System.out.println("id das transacoes FINALIZADAS:");
		while(it.hasNext()){
			System.out.print(transacoes.get(it.next()).getId() +  " ");	
		}
		System.out.println();
		
	}
	
	
	public static void mostrar_eventos(Grafo grafo){
		mostrar_iniciada(grafo);
		mostrar_ativa(grafo);
		mostrar_efetivacao(grafo);
		mostrar_cancelamento(grafo);
		mostrar_efetivada(grafo);
		mostrar_finalizada(grafo);
	}
	
	
	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int opcao=1, evento, id;
		Grafo grafo = new Grafo();
		boolean bool;
		
		
		while(opcao!=0){
			//escolher o evento para a trasacao.
			bool = false;

			System.out.println("Escolha uma opcao:  \n 0) Encerrar \n 1) Criar Evento \n 2) Mostrar eventos" ); 
			opcao = scan.nextInt();
			
			if(opcao == 1){
				System.out.println("Que tipo de evento deseja iniciar?");
				System.out.println(" 1) TR_Begin \n 2) READ \n 3) WRITE \n 4) TR_Terminate \n 5) TR_Rollback \n 6) TR_Commit \n 7) TR_Finish \n ");
				evento = scan.nextInt();
				
				if (evento == 1 ){
					criar_transacao(grafo);
					System.out.println("Evento concluido com sucesso!");

				}else{
					System.out.println("Digite o ID da transacao:");
					id = scan.nextInt();
					
					switch (evento) {
					case 2:
						bool = grafo.read(id);
						break;
						
					case 3:
						bool = grafo.write(id);
						break;
						
					case 4:
						bool = grafo.tr_terminate(id);
						break;
						
					case 5:
						bool = grafo.tr_rollback(id);
						break;
						
					case 6:
						bool = grafo.tr_commit(id);
						break;
						
					case 7:
						bool = grafo.tr_finish(id);
						break;
		
					default:
						System.out.println("Opcao Invalida!");
						break;
					}
					
					if (bool == true){
						System.out.println("Evento concluido com sucesso!");
					}else System.out.println("Nenhuma acao a ser feita");
				}
					
				
			}
							
			if(opcao == 2)
				mostrar_eventos(grafo);	
			
			if(opcao != 0 && opcao != 1 && opcao != 2)
				System.out.println("Opcao invalida!");
		}
		
		
		
		
		
	 
		
		
	}

}
