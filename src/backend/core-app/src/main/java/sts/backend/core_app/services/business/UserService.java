package sts.backend.core_app.services.business;

import java.util.List;

import org.springframework.stereotype.Service;

import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.User;
import sts.backend.core_app.services.analysis.interfaces.BasicDataAnalysis;


@Service
public class UserService {
    
    private final BasicDataAnalysis basicDataAnalysis;
    
    public UserService(BasicDataAnalysis basicDataAnalysis) {
        this.basicDataAnalysis = basicDataAnalysis;
    }

    public List<User> getUsers() throws ResourceNotFoundException {
        return basicDataAnalysis.getUsers();
    }

    public void updateUser(Long userId, String profilePictureUrl) throws ResourceNotFoundException {
        basicDataAnalysis.updateUser(userId, profilePictureUrl);
    }

    public void deleteUser(Long userId) throws ResourceNotFoundException {
        basicDataAnalysis.deleteUser(userId);
    }
    
}


