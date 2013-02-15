package codigosDosTestes;

import java.util.ArrayList;
import java.util.List;
import easyaccept.EasyAcceptFacade;
import mainClasses.Sistema;

public class TesteUserStories {

	public static void main(String[] args) {

		List<String> files = new ArrayList<String>();
		
		// Put the us1.txt file into the "test scripts" list
		files.add("/home/thalysson/workspace/ProjetoSI1/src/testesDeAceitacao_SI1-master/scripts/US01.txt");

		// Instantiate the Monopoly Game façade
		Sistema testeDoSistema = new Sistema();

		// Instantiate EasyAccept facade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(testeDoSistema,files);

		// Execute the tests
		eaFacade.executeTests();

		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());

	}

}
