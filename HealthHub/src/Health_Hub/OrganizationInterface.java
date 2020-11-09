package Health_Hub;

import Client.ClientInterface;
import Staff.ManagerInterface;
import Staff.Owner;

public interface OrganizationInterface {
    void SetOrgName(String name);

    void SetInstructors(InstructorInterface instructor);

    void SetManagers(ManagerInterface manager);

    void SetClient(ClientInterface client);

    void SetOwner(Owner owner);
}
