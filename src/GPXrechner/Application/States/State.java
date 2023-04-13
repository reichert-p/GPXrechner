package GPXrechner.Application.States;

import GPXrechner.Domain.WayModel.WayModel.Entities.Path;

public interface State {
    Path getPath();
}
