package us.martink.Command.CommandConcrete;

import org.springframework.beans.factory.annotation.Autowired;
import us.martink.Command.CommandAbstraction.Command;
import us.martink.Command.CommandAbstraction.IUndoCommand;
import us.martink.Domain.DomainAbstraction.President.President;
import us.martink.Domain.DomainAbstraction.President.PresidentRepository;
import us.martink.UI.UIAbstraction.INewsView;

/**
 * Created by tadas.
 */
public class CheckPresidents extends Command implements IUndoCommand {

    private INewsView dialog;
    private PresidentRepository repo;

    public CheckPresidents(INewsView dialog, PresidentRepository repo) {
        this.repo = repo;
        this.dialog = dialog;
        setName("Check presidents");
    }

    @Override
    public void execute() {
        dialog.show(repo);
    }
}
