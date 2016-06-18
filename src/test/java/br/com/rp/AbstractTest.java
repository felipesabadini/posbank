package br.com.rp;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;

import br.com.rp.domain.Log;
import br.com.rp.repository.LogRepositoryTest;
import br.com.rp.repository.Repository;
import br.com.rp.repository.impl.AbstractRepositoryImpl;
import br.com.rp.repository.service.LogServiceTest;
import br.com.rp.repository.service.PropostaServiceTest;
import br.com.rp.service.ClienteServiceTest;
import br.com.rp.services.LogService;
import br.com.rp.services.PropostaService;
import br.com.rp.services.exception.SaldoInsuficienteException;
import br.com.rp.services.impl.ClienteServiceImpl;
import br.com.rp.services.impl.PropostaServiceImpl;
import br.com.rp.util.Util;


@RunWith(Arquillian.class)
public abstract class AbstractTest {

	@PersistenceContext
	protected EntityManager em;

	/*@Before
	public void setup() {

	}

	@After
	public void tearDown() {
		
	}*/

	@Deployment(testable = true)
	public static WebArchive createTestArchive() {

		File[] deps = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeAndTestDependencies().resolve()
				.withTransitivity().asFile();

		WebArchive archive = ShrinkWrap.create(WebArchive.class, "vbank.war")
				.addPackages(false, Log.class.getPackage())
				.addPackages(false, Repository.class.getPackage())
				.addPackages(false, AbstractRepositoryImpl.class.getPackage())
				.addPackages(false, AbstractTest.class.getPackage())
				.addPackages(false, ClienteServiceTest.class.getPackage())
				.addPackages(false, LogRepositoryTest.class.getPackage())
				.addPackages(false, LogService.class.getPackage())
				.addPackages(false, LogServiceTest.class.getPackage())
				.addPackages(false, ClienteServiceImpl.class.getPackage())
				.addPackages(false, SaldoInsuficienteException.class.getPackage())	
				.addPackages(false, PropostaServiceTest.class.getPackage())
				.addPackages(false, PropostaService.class.getPackage())
				.addPackages(false, PropostaServiceImpl.class.getPackage())				
				.addPackages(false, Util.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsWebInfResource("cesumar-ds.xml")
				.addAsLibraries(deps);

		return archive;
	}
}