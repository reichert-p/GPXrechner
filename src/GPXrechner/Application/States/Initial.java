package GPXrechner.Application.States;

import GPXrechner.Domain.WayModel.WayModel.Entities.Path;

public class Initial implements State {
    @Override
    public Path getPath() throws NullPointerException {
        throw new NullPointerException();
    }
}
