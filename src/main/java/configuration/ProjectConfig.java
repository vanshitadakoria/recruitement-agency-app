/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

/**
 *
 * @author vanshita
 */

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/recruitmentApp",
        callerQuery = "select password from users where email = ?",
        groupsQuery = "select role_name from users join roles_permissions on roles_permissions.id = users.role_id where email = ?",
        hashAlgorithm = CustomBCryptPasswordHash.class,
        priority = 30)


@Named
@ApplicationScoped
public class ProjectConfig {
     public ProjectConfig() {
        System.out.println("Project Config Initialized");
    }
}
