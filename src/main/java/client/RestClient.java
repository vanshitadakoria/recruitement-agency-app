/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Jersey REST client generated for REST resource:RestResource [rest]<br>
 * USAGE:
 * <pre>
 *        RestClient client = new RestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author vanshita
 */
public class RestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "https://localhost:8181/RecruitmentAgencyApp/resources";

    public RestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rest");
    }

    public <T> T getAllUsers(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getallusers");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUsersByPlanId(Class<T> responseType, String pid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getusersbyplanid/{0}", new Object[]{pid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeUser(String uid, String role_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteuser/{0}/{1}", new Object[]{uid, role_id})).request().delete();
    }

    public void addUser(String name, String email, String phone, String dob, String password, String vpa, String role_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("adduser/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{name, email, phone, dob, password, vpa, role_id})).request().post(null);
    }
    
    public void addPlanTransactions(String payment_method, String email, String contact_no, String card_number, String card_holder_name, String expiry_date, String cvv, String bank_name, String bank_transaction_id, String upi_transaction_id, String amount, String error_reason, String pid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addplantransactions/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}/{10}/{11}/{12}", new Object[]{payment_method, email, contact_no, card_number, card_holder_name, expiry_date, cvv, bank_name, bank_transaction_id, upi_transaction_id, amount, error_reason, pid})).request().post(null);
    }
//    public void updateUser(Object requestEntity, String uid, String name, String email, String phone, String dob, String password, String vpa, String role_id) throws ClientErrorException {
//        webTarget.path(java.text.MessageFormat.format("updateuser/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{uid, name, email, phone, dob, password, vpa, role_id})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
//    }
    
    public void updateUser(String uid, String name, String email, String phone, String dob, String password, String vpa, String role_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateuser/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{uid, name, email, phone, dob, password, vpa, role_id})).request().put(Entity.entity("", MediaType.APPLICATION_JSON));
    }

    public <T> T findUserById(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("finduserbyid/{0}", new Object[]{uid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findUserByEmail(Class<T> responseType, String email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("finduserbyemail/{0}", new Object[]{email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
    
    


    public void close() {
        client.close();
    }
    
}
