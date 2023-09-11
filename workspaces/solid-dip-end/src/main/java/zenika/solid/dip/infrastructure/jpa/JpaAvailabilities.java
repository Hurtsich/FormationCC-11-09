package zenika.solid.dip.infrastructure.jpa;

import zenika.solid.dip.domain.availability.Availabilities;

public class JpaAvailabilities implements Availabilities {

    @Override public boolean isAvailable() {
        //En réalité, il y aurait une dépendance vers JPA/Hib/un WS...
        return true;
    }

}
