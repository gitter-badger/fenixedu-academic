package ServidorAplicacao.Servico.masterDegree.administrativeOffice.candidate;

import java.util.Calendar;
import java.util.Iterator;

import DataBeans.InfoExecutionDegree;
import DataBeans.InfoPerson;
import DataBeans.util.Cloner;
import Dominio.CandidateSituation;
import Dominio.ICandidateSituation;
import Dominio.ICursoExecucao;
import Dominio.IMasterDegreeCandidate;
import Dominio.IPessoa;
import Dominio.MasterDegreeCandidate;
import ServidorAplicacao.IServico;
import ServidorAplicacao.Servico.ExcepcaoInexistente;
import ServidorAplicacao.Servico.exceptions.FenixServiceException;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistente.ICursoExecucaoPersistente;
import ServidorPersistente.IPessoaPersistente;
import ServidorPersistente.ISuportePersistente;
import ServidorPersistente.OJB.SuportePersistenteOJB;
import ServidorPersistente.exceptions.ExistingPersistentException;
import Util.SituationName;
import Util.State;

/**
 * @author Nuno Nunes (nmsn@rnl.ist.utl.pt)
 *         Joana Mota (jccm@rnl.ist.utl.pt)
 * 
 * This Service Changes the Candidate Situation from Pre-Candidate to Pending
 * 
 */

public class CreateCandidateSituation implements IServico {

	private static CreateCandidateSituation servico = new CreateCandidateSituation();

	/**
	 * The singleton access method of this class.
	 **/
	public static CreateCandidateSituation getService() {
		return servico;
	}

	/**
	 * The actor of this class.
	 **/
	private CreateCandidateSituation() {
	}

	/**
	 * Returns The Service Name */

	public final String getNome() {
		return "CreateCandidateSituation";
	}

	// FIXME: Should this receive the new Situation ?

	public void run(InfoExecutionDegree infoExecutionDegree, InfoPerson infoPerson)
		throws FenixServiceException {

		IMasterDegreeCandidate masterDegreeCandidate = new MasterDegreeCandidate();

		ISuportePersistente sp = null;

		try {
			sp = SuportePersistenteOJB.getInstance();
			IPessoaPersistente persistentPerson = sp.getIPessoaPersistente();
			ICursoExecucaoPersistente persistentExecutionDegree = sp.getICursoExecucaoPersistente();
			ICursoExecucao executionDegree =
				Cloner.copyInfoExecutionDegree2ExecutionDegree(infoExecutionDegree);
			if (executionDegree.getIdInternal() != null) {
				executionDegree =
					(ICursoExecucao) persistentExecutionDegree.readByOId(executionDegree, false);
			}

			IPessoa person = Cloner.copyInfoPerson2IPerson(infoPerson);
			if (person.getIdInternal() != null) {
				person = (IPessoa) persistentPerson.readByOId(person, false);
			}

			masterDegreeCandidate =
				sp.getIPersistentMasterDegreeCandidate().readByExecutionDegreeAndPerson(
					executionDegree,
					person);
			if (masterDegreeCandidate == null)
				throw new ExcepcaoInexistente("Unknown Master Degree Candidate !!");

			sp.getIPersistentMasterDegreeCandidate().writeMasterDegreeCandidate(
				masterDegreeCandidate);
			Iterator iterator = masterDegreeCandidate.getSituations().iterator();
			while (iterator.hasNext()) {
				ICandidateSituation candidateSituation = (ICandidateSituation) iterator.next();
				if (candidateSituation.getValidation().equals(new State(State.ACTIVE))) {
					ICandidateSituation candidateSituationTemp = new CandidateSituation();
					candidateSituationTemp.setIdInternal(candidateSituation.getIdInternal());
					ICandidateSituation candidateSituationFromBD =
						(ICandidateSituation) sp.getIPersistentCandidateSituation().readByOId(
							candidateSituationTemp,
							true);
					candidateSituationFromBD.setValidation(new State(State.INACTIVE));
				}
			}

			// Create the New Candidate Situation			
			ICandidateSituation candidateSituation = new CandidateSituation();
			sp.getIPersistentCandidateSituation().simpleLockWrite(candidateSituation);
			Calendar calendar = Calendar.getInstance();
			candidateSituation.setDate(calendar.getTime());
			candidateSituation.setSituation(new SituationName(SituationName.PENDENTE_STRING));
			candidateSituation.setValidation(new State(State.ACTIVE));
			candidateSituation.setMasterDegreeCandidate(masterDegreeCandidate);
			sp.getIPersistentCandidateSituation().writeCandidateSituation(candidateSituation);
		

		} catch (ExistingPersistentException ex) {
			// The situation Already Exists ... Something wrong ?
		} catch (ExcepcaoPersistencia ex) {
			FenixServiceException newEx = new FenixServiceException("Persistence layer error", ex);
			throw newEx;
		}
	}

}
