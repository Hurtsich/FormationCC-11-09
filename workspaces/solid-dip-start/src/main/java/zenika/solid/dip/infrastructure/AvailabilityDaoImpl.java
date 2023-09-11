package zenika.solid.dip.infrastructure;

public class AvailabilityDaoImpl implements AvailabilityDao {

    @Override public boolean isAvailable() {
        //En réalité, il y aurait une dépendance vers JPA/Hib/un WS...
        return true;
    }

}
