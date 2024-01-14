/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import configuration.ConfigReader;
import ejb.UserBeanLocal;
import entities.Candidates;
import entities.Users;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import static sun.font.CreatedFontTracker.MAX_FILE_SIZE;

/**
 *
 * @author vanshita
 */
@Named(value = "candidateMB")
@ViewScoped
public class candidatesMB implements Serializable {

    @ManagedProperty("#{configReader}")
    private ConfigReader configReader;

    Candidates c;
    Collection<Candidates> candidates;
    Collection<Candidates> candidatesOfUser;
    Integer candidatesCounts;
    
    private Part resume_file;
    private String uploadFolderPath;
    private Integer selectedJob;
    private Integer userId;
    Candidates selC;
    

    @EJB
    UserBeanLocal userBean;

    public Candidates getC() {
        return c;
    }

    public void setC(Candidates c) {
        this.c = c;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Candidates getSelC() {
        return selC;
    }

    public void setSelC(Candidates selC) {
        this.selC = selC;
    }
    
    
    public Collection<Candidates> getCandidates() {
        candidates = userBean.getAllCandidates();
        return candidates;
    }

    public void setCandidates(Collection<Candidates> candidates) {
        this.candidates = candidates;
    }

    public Collection<Candidates> getCandidatesOfUser(Object user_email) {
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        String user_email = request.getSession().getAttribute("user-email").toString();
        Users userByEmail = null;
        if (user_email != null) {

            userByEmail = userBean.findUserByEmail(user_email.toString());
        } else {
            System.out.print("Session email found null");
        }
        candidatesOfUser = userBean.getCandidatesByUserId(userByEmail.getId());
        return candidatesOfUser;
    }

    public void setCandidatesOfUser(Collection<Candidates> candidatesOfUser) {
        this.candidatesOfUser = candidatesOfUser;
    }

    public Integer getCandidatesCounts(Object user_email) {
        Users userByEmail = null;
        System.out.print("Session email" + user_email);

        if (user_email != null) {

            userByEmail = userBean.findUserByEmail(user_email.toString());
        } else {
            System.out.print("Session email found null");
        }
        candidatesCounts = userBean.getCandidatesCountsOfUser(userByEmail.getId());
        return candidatesCounts;
    }

    public void setCandidatesCounts(Integer candidatesCounts) {
        this.candidatesCounts = candidatesCounts;
    }

    
    
    
    public Part getResume_file() {
        return resume_file;
    }

    public void setResume_file(Part resume_file) {
        this.resume_file = resume_file;
    }

    public ConfigReader getConfigReader() {
        return configReader;
    }

    public void setConfigReader(ConfigReader configReader) {
        this.configReader = configReader;
    }

    public String getUploadFolderPath() {
        return uploadFolderPath;
    }

    public void setUploadFolderPath(String uploadFolderPath) {
        this.uploadFolderPath = uploadFolderPath;
    }

    public Integer getSelectedJob() {
        return selectedJob;
    }

    public void setSelectedJob(Integer selectedJob) {
        this.selectedJob = selectedJob;
    }

    public void handleFileUpload() {
        if (resume_file != null) {
            FacesMessage message;
            
            if (resume_file.getSize() > MAX_FILE_SIZE) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "File size exceeds the maximum allowed size.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                message = new FacesMessage("Successful", resume_file.getSubmittedFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public String addCandidate() {

        System.out.print("In add candidate: ");
        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String user_email = request.getSession().getAttribute("user-email").toString();
            Users userByEmail = null;
            if (user_email != null) {

                userByEmail = userBean.findUserByEmail(user_email);
            } else {
                System.out.print("Session email found null");
            }

            // Get the upload folder path
            uploadFolderPath = configReader.getUploadFolderPath();
            if (uploadFolderPath == null) {
                return "error";
            }

//            // Generate a unique filename
//            String fileName = System.currentTimeMillis() + "_" + resume_file.getSubmittedFileName();
//
//            // Upload the file to the folder and get the file path
//            String filePath = userBean.uploadFile(resume_file.getInputStream(), uploadFolderPath, fileName);
//            //Path targetPath = Paths.get("/home/vanshita/Documents/ICT sem-1/Projects Netbeans 17/assignment 3/FashionServlet/src/main/webapp/product-images/", fileName);
//
//            if(c != null){
//                userBean.addCandidate(c.getFirstname(), c.getLastname(), c.getEmail(), "uploads/" + fileName, c.getPhoneNo(), userByEmail.getId(), selectedJob);
//            }
//
//            return "candidates.jsf"; // navigate to a success page
//            
//            
            // Upload the file only if it is a PDF
            if (resume_file.getSubmittedFileName().toLowerCase().endsWith(".pdf")) {
                // Generate a unique filename
                String fileName = System.currentTimeMillis() + "_" + resume_file.getSubmittedFileName();

                // Upload the file to the folder and get the file path
                String filePath = userBean.uploadFile(resume_file.getInputStream(), uploadFolderPath, fileName);

                // Continue with the rest of your code...
                if (c != null) {
                    userBean.addCandidate(c.getFirstname(), c.getLastname(), c.getEmail(), "uploads/" + fileName, c.getPhoneNo(), userByEmail.getId(), selectedJob);
                }

                return "candidates.jsf"; // navigate to a success page
            } else {
                // Handle the case where the uploaded file is not a PDF
                FacesMessage message = new FacesMessage("Invalid file format. Please upload a PDF file.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return null; // or return an error outcome if needed
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            return "error"; // navigate to an error page
        }
    }

    public String deleteCandidate(Candidates candidate) {
        if (candidate != null) {
            userBean.removeCandidate(candidate.getId(), candidate.getJobId().getId(), candidate.getUserId().getId());
        }
        return "candidates.jsf?faces-redirect=true";
    }

    public void editCandidate(Candidates candidate) {
        System.out.print(" editCandidate() : " + candidate.getId() + " " + candidate.getFirstname() + "");

        selC = candidate;
        selectedJob = candidate.getJobId().getId();

        c.setId(candidate.getId());
        c.setFirstname(candidate.getFirstname());
        c.setLastname(candidate.getLastname());
        c.setEmail(candidate.getEmail());
        c.setPhoneNo(candidate.getPhoneNo());
        c.setResumeFile(candidate.getResumeFile());
        c.setUserId(candidate.getUserId());
        
        System.out.print(" selC : " + selC.getId() + " " + selC.getFirstname() + " " + selC.getLastname() + " " +selC.getResumeFile() + "");

    }

    public String update() {
        System.out.print(" update() : " + c.getId() + " " + c.getFirstname() + " user : " + c.getUserId().getName());
        if(selC != null){
            System.out.print("selC id: " + selC.getId());
            System.out.print("selC resume: " + selC.getResumeFile() + "");

            selC.setFirstname(c.getFirstname());
            selC.setLastname(c.getLastname());
            selC.setPhoneNo(c.getPhoneNo());
            selC.setEmail(c.getEmail());
            
            userBean.updateCandidate(selC.getId(), selC.getFirstname(), selC.getLastname(), selC.getEmail(), selC.getResumeFile(), selC.getPhoneNo(), selC.getUserId().getId(), selectedJob);
            
            c = new Candidates();
            selectedJob = null;
            selC = null;
        }else {
            System.out.print("SelC is null");
        }
//        

        return "candidates.jsf?faces-redirect=true";
    }
//    public String update() {
//        System.out.print("c : " + c.getId() + " " + c.getFirstname() + " " + c.getLastname() + " " + c.getResumeFile() + "");
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        String user_email = request.getSession().getAttribute("user-email").toString();
//        System.out.print("user-email : " + user_email);
//
//        if (user_email != null) {
//            Users userByEmail = userBean.findUserByEmail(user_email);
//
//            userBean.updateCandidate(c.getId(), c.getFirstname(), c.getLastname(), c.getEmail(), file, c.getPhoneNo(), userByEmail.getId(), selectedJob);
//            this.c = new Candidates();
//            this.selectedJob = null;
//        } else {
//            System.out.print("Session email found null");
//        }
//
//        return "candidates.jsf?faces-redirect=true";
//    }

    /**
     * Creates a new instance of candidatesMB
     */
    public candidatesMB() {
        c = new Candidates();
    }

}
