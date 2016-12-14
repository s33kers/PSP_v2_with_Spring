package us.martink.Command.CommandConcrete;

import org.springframework.beans.factory.annotation.Autowired;
import us.martink.Command.CommandAbstraction.Command;
import us.martink.Command.CommandAbstraction.IUndoableCommand;
import us.martink.Domain.DomainAbstraction.Agent.Agent;
import us.martink.Domain.DomainAbstraction.Agent.AgentRepository;
import us.martink.Domain.DomainAbstraction.IGovernmentFactory;
import us.martink.Domain.DomainAbstraction.President.President;
import us.martink.Domain.DomainAbstraction.President.PresidentRepository;
import us.martink.UI.UIAbstraction.PresidentIdDialog;

/**
 * Created by tadas.
 */
public class HireAgentForPresident extends Command implements IUndoableCommand {

    PresidentIdDialog dialog;
    private PresidentRepository presidents;
    private AgentRepository agents;
    IGovernmentFactory factory;
    int presidentID;
    int agentID;

    public HireAgentForPresident(PresidentIdDialog dialog,
                                 IGovernmentFactory factory,
                                 PresidentRepository presidents,
                                 AgentRepository agents) {
        this.presidents = presidents;
        this.agents = agents;
        this.dialog = dialog;
        this.factory = factory;
        setName("Hire agent for selected president");
    }

    @Override
    public void execute() {
        presidentID = dialog.show();

        President president = presidents.getElementByID(presidentID);

        if (president != null) {
            Agent agent = factory.createAgent();
            agents.addElement(agent);
            agentID = agent.getID();

            president.registerObserver(agent);
        }
    }

    @Override
    public void unExecute() {
        if (presidentID != 0) {
            President president = presidents.getElementByID(presidentID);
            Agent agent = agents.getElementByID(agentID);
            president.unRegisterObserver(agent);
        }
    }
}
