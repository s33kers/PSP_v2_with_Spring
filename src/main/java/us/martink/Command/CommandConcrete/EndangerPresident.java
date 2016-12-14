package us.martink.Command.CommandConcrete;

import org.springframework.beans.factory.annotation.Autowired;
import us.martink.Command.CommandAbstraction.Command;
import us.martink.Command.CommandAbstraction.IUndoableCommand;
import us.martink.Domain.DomainAbstraction.President.President;
import us.martink.Domain.DomainAbstraction.President.PresidentMemento;
import us.martink.Domain.DomainAbstraction.President.PresidentRepository;
import us.martink.UI.UIAbstraction.PresidentIdDialog;

import java.util.Stack;

/**
 * Created by tadas.
 */
public class EndangerPresident extends Command implements IUndoableCommand {
    private PresidentIdDialog dialog;
    private PresidentRepository presidents;
    private Stack<PresidentMemento> mementoes = new Stack<>();

    public EndangerPresident(PresidentIdDialog dialog, PresidentRepository presidents) {
        this.presidents = presidents;
        this.dialog = dialog;
        setName("Endanger selected president");
    }

    @Override
    public void execute() {
        int presidentID = dialog.show();

        President president = presidents.getElementByID(presidentID);

        if (president != null) {
            mementoes.push(president.createMemento());
            president.endangerMySelf();
        }
    }

    @Override
    public void unExecute() {
        if (mementoes != null) {
            PresidentMemento presidentMemento = mementoes.pop();
            President president = presidents.getElementByID(presidentMemento.ID);
            president.restoreFromMemento(presidentMemento);
        }

    }
}
