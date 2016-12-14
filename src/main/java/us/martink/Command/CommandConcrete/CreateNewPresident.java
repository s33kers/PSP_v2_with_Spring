package us.martink.Command.CommandConcrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import us.martink.Command.CommandAbstraction.Command;
import us.martink.Domain.DomainAbstraction.IGovernmentFactory;
import us.martink.Domain.DomainAbstraction.President.President;
import us.martink.Domain.DomainAbstraction.President.PresidentRepository;
import us.martink.UI.UIAbstraction.IPresidentView;

/**
 * Created by tadas.
 */
public class CreateNewPresident extends Command{

    private IGovernmentFactory factory;
    private PresidentRepository repo;
    private String firstName;
    private String lastName;
    private IPresidentView view;

    public CreateNewPresident(IGovernmentFactory factory, String firstName, String lastName, IPresidentView presidentsView, PresidentRepository repo)
    {
        this.factory = factory;
        this.repo = repo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.view = presidentsView;
        setName("Create new president");

    }
    @Override
    public void execute() {
        President president = factory.createPresident(firstName, lastName);
        president.registerObserver(view);
        repo.addElement(president);
    }
}
