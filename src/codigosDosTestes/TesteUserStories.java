package codigosDosTestes;

import java.util.ArrayList;
import java.util.List;
import easyaccept.EasyAcceptFacade;
import mainclasses.Sistema;

public class TesteUserStories {

	public static void main(String[] args) {

		List<String> files = new ArrayList<String>();
		files.add("/home/thalysson/workspace/ProjetoSI1/src/testesDeAceitacao_SI1-master/scripts/US01.txt");		
		files.add("/home/thalysson/workspace/ProjetoSI1/src/testesDeAceitacao_SI1-master/scripts/US02.txt");
		files.add("/home/thalysson/workspace/ProjetoSI1/src/testesDeAceitacao_SI1-master/scripts/US03.txt");
		files.add("/home/thalysson/workspace/ProjetoSI1/src/testesDeAceitacao_SI1-master/scripts/US04.txt");
		

		Sistema testeDoSistema = new Sistema();

		// Instantiate EasyAccept facade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(testeDoSistema,files);
		
		// Execute the tests
		eaFacade.executeTests();

		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());

	}

}
